package study.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class MqConnector implements Runnable {

	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("***********************************");
			Context context = new InitialContext();
			QueueConnectionFactory factory = (QueueConnectionFactory) context
					.lookup("java:comp/env/jms/NormalConnectionFactory");
			Connection connection = factory.createConnection();
			Queue queue = (javax.jms.Queue) context
					.lookup("java:comp/env/jms/queue/MyQueue");
			Topic topic = (javax.jms.Topic)context.lookup("java:comp/env/jms/topic/MyTopic");
			Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);

			MessageConsumer consumer = session.createConsumer(queue);
			MessageConsumer consumerT = session.createConsumer(topic);
			// This MessageListener will do stuff with the message
			consumer.setMessageListener(new MessageListener() {

				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println(msg.getText());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			});
			consumerT.setMessageListener(new MessageListener() {
				
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					TextMessage msg = (TextMessage) message;
					try {
						System.out.println("topic:"+msg.getText());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
			connection.start();

			// Start connection or nothing will happen!!!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

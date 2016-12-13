package study.activemq;


import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqClient {
	private  ConnectionFactory connectionFactory;
	private  Connection connection;
	private static  Session session;
//	private static final ActiveMqClient instance = new ActiveMqClient();

	public ActiveMqClient() {
		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD,
				"tcp://192.168.76.128:61616");
		try {
			// 构造从工厂得到连接对象
			connection = connectionFactory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			session = connection.createSession(Boolean.FALSE,
					Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			// destination = session.createQueue("FirstQueue1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static  Destination getTopicDestination(String topicName)
			throws JMSException {
		return session.createTopic(topicName);
	}
	
	public  Destination getQueueDestination(String queueName)
			throws JMSException {
		return session.createQueue(queueName);
	}
	
	public static  MessageConsumer getTopicConsumer(String topicName) {
		try {
			return session.createConsumer(getTopicDestination(topicName));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public  MessageConsumer getQueueConsumer(String queueName) throws JMSException {
		return session.createConsumer(getQueueDestination(queueName));
	}
	
}

package study.activemq;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ActiveMqClientTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread th = new Thread(new Runnable() {
			
			public void run() {
				// TODO Auto-generated method stub
				try {
					MessageConsumer consumer = ActiveMqClient
							.getTopicConsumer("testQueue");
					consumer.setMessageListener(new MessageListener() {

						public void onMessage(Message message) {
							// TODO Auto-generated method stub
							TextMessage msg = (TextMessage) message;
							try {
								
								String mes= msg.getText();
								System.out.println(mes);
								URL url = new URL("http://localhost:8080/study/ActiveMqServlet?message="+mes);
								HttpURLConnection conn = (HttpURLConnection)url.openConnection();
								System.out.println(conn.getResponseCode());
								conn.connect();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

					});
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		th.start();
		
	}

}

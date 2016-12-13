package study.activemq;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class ActiveMqClientListener
 *
 */
public class ActiveMqClientListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

    /**
     * Default constructor. 
     */
    public ActiveMqClientListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
		System.out.println("创建session"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

	/**
     * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
     */
    public void requestDestroyed(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
     */
    public void requestInitialized(ServletRequestEvent sre)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	System.out.println("创建context"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    	Thread thread = new Thread(new MqConnector());
    	 thread.start();
    	
    	/*ActiveMqClient amc = new ActiveMqClient();
    	MessageConsumer consumer = amc
						.getTopicConsumer("testQueue");*/
/*	consumer.setMessageListener(new MessageListener() {

					public void onMessage(Message message) {
						// TODO Auto-generated method stub
						TextMessage msg = (TextMessage) message;
						try {
							System.out.println(msg.getText());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				});*/
    	
    }
	
}

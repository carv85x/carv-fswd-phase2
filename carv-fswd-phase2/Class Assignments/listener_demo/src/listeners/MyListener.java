package listeners;

import java.util.Date;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyListener
 *
 */
public class MyListener implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MyListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0)  { 
         System.out.println("Hello from listener. Session created at " 
        		 			+ new Date(arg0.getSession().getCreationTime()));
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0)  { 
        System.out.println("Hello from listener. Session destroyed at " 
	 						+ new Date(arg0.getSession().getCreationTime()));
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
    	System.out.println("From listener key with last " 
					+ arg0.getName() 
					+ " and value is "
					+ arg0.getValue()
					+ " added successfully");
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
    	System.out.println("From listener key with last " 
					+ arg0.getName() 
					+ " and value is "
					+ arg0.getValue()
					+ " removed successfully");
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
    	System.out.println("From listener key with last " 
					+ arg0.getName() 
					+ " and value is "
					+ arg0.getValue()
					+ " modified successfully");
    }
	
}

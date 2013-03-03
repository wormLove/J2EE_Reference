/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypackage;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Web application lifecycle listener.
 * @author wjlwyz
 */
public class MyOwnEventListener implements ServletContextListener, HttpSessionListener, ServletRequestListener,HttpSessionAttributeListener{

    ServletContext servletContext;


    public void contextInitialized(ServletContextEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext  = evt.getServletContext();
        servletContext.log("contextInitialized() method is invoked");
    }

    public void contextDestroyed(ServletContextEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext.log("contextDestroyed() method is invoked");
    }

    public void sessionCreated(HttpSessionEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext.log("sessionCreated() method is invoked");
    }

    public void sessionDestroyed(HttpSessionEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext.log("sessionDestroyed() method is invoked");

    }

    public void requestDestroyed(ServletRequestEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext.log("requestDestroyed() method is invoked");
    }

    public void requestInitialized(ServletRequestEvent evt) {
        //throw new UnsupportedOperationException("Not supported yet.");
        servletContext.log("requestInitialized() method is invoked");
    }

    public void attributeAdded(HttpSessionBindingEvent evt) {
        servletContext.log("attributeAdded() method is invoked");
    }

    public void attributeRemoved(HttpSessionBindingEvent evt) {
        servletContext.log("attributeRemoved() method is invoked");
    }

    public void attributeReplaced(HttpSessionBindingEvent evt) {
        servletContext.log("attributeReplaced() method is invoked");
    }
}

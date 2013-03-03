/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

/**
 *
 * @author Administrator
 */
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.*;

public class MyLoggingInterceptor implements Interceptor {

    private static final String LOGIN="login";


    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map session = invocation.getInvocationContext().getSession();
        String userName = (String) session.get("userName");

        if (null != userName && userName.equals("test")) {
            System.out.println("拦截器：合法用户登录---");
            return invocation.invoke();
        } else {
            System.out.println("拦截器：用户未登录---");
            return LOGIN;
        }
    }

  
    @Override
    public void destroy() {
        System.out.println("Destroying MyLoggingInterceptor...");
    }

    @Override
    public void init() {
        System.out.println("Initializing MyLoggingInterceptor...");
    }
}

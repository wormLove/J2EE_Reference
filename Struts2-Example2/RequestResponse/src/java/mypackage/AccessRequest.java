/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

/**
 *
 * @author Administrator
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

public class AccessRequest extends ActionSupport implements
        ServletRequestAware, ServletResponseAware {

    private HttpServletRequest servletRequest;
    private HttpServletResponse servletResponse;

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.servletRequest = request;
    }

    public HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.servletResponse = response;
    }

    public HttpServletResponse getServletResponse() {
        return servletResponse;
    }
}

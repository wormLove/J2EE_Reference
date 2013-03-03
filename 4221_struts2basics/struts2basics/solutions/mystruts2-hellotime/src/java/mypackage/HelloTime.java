package mypackage;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Date;

public class HelloTime extends ActionSupport {

    public static final String MESSAGE = "My Own Struts 2 Hello Time Tutorial!";

    public String execute() throws Exception {
        setMessage(MESSAGE);
        return SUCCESS;
    }
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCurrentTime() {
        return new Date().toString();
    }
}

package mypackage;

import com.opensymphony.xwork2.ActionSupport;

public class HelloTime extends ActionSupport {
    public String execute() throws Exception {
        return SUCCESS;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

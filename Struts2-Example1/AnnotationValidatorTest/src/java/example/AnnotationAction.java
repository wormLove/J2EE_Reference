package example;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.validator.annotations.*;
//@Validation()
public class AnnotationAction extends ActionSupport {

    private String username = null;
    private String password = null;

    @RequiredStringValidator(message = "Supply name")
    public String getUsername() {

        return username;
    }

    
    public void setUsername(String value) {

        username = value;
    }

    @RequiredStringValidator(message = "Supply password")
    public String getPassword() {

        return password;
    }

    public void setPassword(String value) {

        password = value;
    }

    public String execute() throws Exception {

        System.out.println("Validating login");
        if (!getUsername().equals("Admin") || !getPassword().equals("Admin")) {
            addActionError("Invalid user name or password! Please try again!");

            return ERROR;

        } else {

            return SUCCESS;
        }

    }
}

package example;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Base Action class for the Tutorial package.
 */
public class ExampleSupport extends ActionSupport {
    @Override
    public String execute() throws Exception {
        LOG.info("ExampleSupport: execute() method is called");
        return SUCCESS;
    }
}

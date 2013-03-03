/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mypackage;

/**
 *
 * @author Administrator
 */
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private String username;
	private String password;

	public String execute() {

		/*if (this.username.equals("admin")
				&& this.password.equals("admin123")) {
			return "success";
		} else {
			addActionError(getText("error.login"));
			return "error";
		}*/
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

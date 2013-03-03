package example;

import com.opensymphony.xwork2.ActionSupport;

public class EmailValidationAction extends ActionSupport {

  private String myEmail;

  public void setMyEmail(String myEmail){
    this.myEmail = myEmail;
  }
  public String getMyEmail(){
    return myEmail;
  }
}
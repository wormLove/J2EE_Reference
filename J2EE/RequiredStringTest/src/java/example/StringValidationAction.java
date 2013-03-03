package example;

import com.opensymphony.xwork2.ActionSupport;

public class StringValidationAction extends ActionSupport{

  private String username;

    @Override
  public String execute() throws Exception{
    if (getUsername() != null){
      return SUCCESS;
    }
    else{
     return ERROR;
    }
  }

 public void setUsername(String username){
    this.username = username;
  }
  public String getUsername(){
    return username;
  }
}
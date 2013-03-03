package example;

import com.opensymphony.xwork2.ActionSupport;

public class NumAction extends ActionSupport{

  private int userinput=0;

  public String execute() throws Exception{

  /*  if (getUserinput() >= 10 && getUserinput() <= 80){
      return SUCCESS;
    }
    else{
      return ERROR;
    }
     */

    return SUCCESS;
  }

  public void setUserinput(int userinput){
    this.userinput = userinput;
  }
  public int getUserinput(){
    return userinput;
  }
}

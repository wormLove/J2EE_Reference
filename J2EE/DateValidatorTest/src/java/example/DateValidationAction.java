package example;

import com.opensymphony.xwork2.*;
import java.util.*;

public class DateValidationAction extends ActionSupport {

  private Date joiningdate=null;

  public void setJoiningdate(Date joiningdate){
    this.joiningdate = joiningdate;
  }
  public Date getJoiningdate(){
    return joiningdate;
  }
}
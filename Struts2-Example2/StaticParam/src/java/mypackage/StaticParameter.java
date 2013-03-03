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
import java.util.Date;

public  class StaticParameter  extends ActionSupport {

  private String pramater1;
  private String pramater2;
  private String pramater3;

    public String execute() throws Exception {
      return SUCCESS;
  }

  public String getPramater1() {
    return pramater1;
  }

  public void setPramater1(String pramater1) {
    this.pramater1 = pramater1;
  }

  public String getPramater2() {
    return pramater2;
  }

  public void setPramater2(String pramater2) {
    this.pramater2 = pramater2;
  }

  public String getPramater3() {
    return pramater3;
  }

  public void setPramater3(String pramater3) {
    this.pramater3 = pramater3;
  }

} 
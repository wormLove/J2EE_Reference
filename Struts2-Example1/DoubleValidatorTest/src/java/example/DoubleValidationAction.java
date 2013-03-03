package example;

import com.opensymphony.xwork2.ActionSupport;

public class DoubleValidationAction extends ActionSupport{

  private double percentagemarks;

  public String execute() throws Exception{

    return SUCCESS;

  /*/*if (getPercentagemarks() > 20.1 && getPercentagemarks() < 50.1){
      return SUCCESS;
    }
    else{
      return ERROR;
    }

*/
  }

  public void setPercentagemarks(double percentagemarks){
    this.percentagemarks = percentagemarks;
  }
  public double getPercentagemarks(){
    return percentagemarks;
  }
}

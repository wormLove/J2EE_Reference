package example;

import com.opensymphony.xwork2.ActionSupport;

public class urlValidationAction extends ActionSupport {

  private String url;

  public void setUrl(String url){
    this.url = url;
  }
  public String getUrl(){
    return url;
  }
} 
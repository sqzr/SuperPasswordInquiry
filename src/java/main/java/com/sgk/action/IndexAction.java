package com.sgk.action;

import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class IndexAction extends ActionSupport
{
  private HttpServletRequest request;
  private HttpSession session;
  private String title = "SGK - Index";

  public IndexAction() {
    this.request = ServletActionContext.getRequest();
    this.session = this.request.getSession();
  }
  public String main() throws Exception {
    return "main";
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
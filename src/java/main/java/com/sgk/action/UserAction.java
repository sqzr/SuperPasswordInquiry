package com.sgk.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sgk.util.CookieUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class UserAction extends ActionSupport
{
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private String title = "SGK - Login";

  public UserAction() {
    this.request = ServletActionContext.getRequest();
    this.response = ServletActionContext.getResponse();
    this.session = this.request.getSession();
  }

  public String login() throws Exception {
    return "hasLogon";
  }

  public String logout() throws Exception {
    this.session.setAttribute("user", null);
    CookieUtil.removeCookie(this.request, this.response, "keeplogin");
    return "success";
  }

  public String getTitle()
  {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
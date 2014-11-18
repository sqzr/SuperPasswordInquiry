package com.sgk.action;

import com.opensymphony.xwork2.ActionSupport;
import com.sgk.model.User;
import com.sgk.service.SearchService;
import com.sgk.service.UserService;
import com.sgk.util.CookieUtil;
import com.sgk.util.EncryptUtil;
import com.sgk.util.RequestInfoUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class AjaxAction extends ActionSupport
{
  private HttpServletRequest request;
  private HttpServletResponse response;
  private HttpSession session;
  private String username;
  private String password;
  private String keepMeLoggedInd;
  private String keyword;
  private UserService userService;
  private SearchService searchService;
  private Map<String, Object> jsonInfo = new HashMap();

  public AjaxAction() {
    this.response = ServletActionContext.getResponse();
    this.request = ServletActionContext.getRequest();
    this.session = this.request.getSession();
  }

  public String login() throws Exception {
    this.request = ServletActionContext.getRequest();
    User user = this.userService.login(this.username, this.password, RequestInfoUtil.getIpAddrAndUserAgent(this.request));
    if (user != null)
    {
      this.session.setAttribute("user", user);
      this.jsonInfo.put("status", Boolean.valueOf(true));
      if ("checked".equals(this.keepMeLoggedInd))
      {
        String keeplogin = EncryptUtil.md5(this.username + this.password + new Date().getTime());
        this.userService.updateKeepLogin(user, keeplogin);

        CookieUtil.setCookie(this.response, "keeplogin", keeplogin, 2592000);
      }
    } else {
      this.jsonInfo.put("status", Boolean.valueOf(false));
    }
    return "login";
  }

  public String search() throws Exception {
    Map userEnvironment = new HashMap();
    userEnvironment.put("user", this.session.getAttribute("user"));
    userEnvironment.put("ip", RequestInfoUtil.getIpAddr(this.request));
    userEnvironment.put("useragent", RequestInfoUtil.getUserAgent(this.request));
    this.jsonInfo = this.searchService.getSearchByKeyword(this.keyword, userEnvironment);
    return "search";
  }

  public String getUsername()
  {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public UserService getUserService() {
    return this.userService;
  }

  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  public Map<String, Object> getJsonInfo() {
    return this.jsonInfo;
  }

  public void setJsonInfo(Map<String, Object> jsonInfo) {
    this.jsonInfo = jsonInfo;
  }

  public SearchService getSearchService() {
    return this.searchService;
  }

  public void setSearchService(SearchService searchService) {
    this.searchService = searchService;
  }

  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public String getKeepMeLoggedInd() {
    return this.keepMeLoggedInd;
  }

  public void setKeepMeLoggedInd(String keepMeLoggedInd) {
    this.keepMeLoggedInd = keepMeLoggedInd;
  }
}
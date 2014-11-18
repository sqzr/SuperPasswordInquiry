package com.sgk.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;

public class ErrorJsonAction extends ActionSupport
{
  private Map<String, Object> jsonInfo = new HashMap();

  public String notlogon() {
    this.jsonInfo.put("status", "notlogon");
    return "success";
  }

  public Map<String, Object> getJsonInfo()
  {
    return this.jsonInfo;
  }

  public void setJsonInfo(Map<String, Object> jsonInfo) {
    this.jsonInfo = jsonInfo;
  }
}
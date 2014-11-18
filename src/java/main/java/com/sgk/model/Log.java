package com.sgk.model;

import java.util.Date;

public class Log
{
  private int id;
  private String type;
  private String content;
  private String result;
  private String ip;
  private String useragent;
  private Date date;

  public int getId()
  {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getContent() {
    return this.content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getIp() {
    return this.ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getUseragent() {
    return this.useragent;
  }

  public void setUseragent(String useragent) {
    this.useragent = useragent;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getResult() {
    return this.result;
  }

  public void setResult(String result) {
    this.result = result;
  }
}
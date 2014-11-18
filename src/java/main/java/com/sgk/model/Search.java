package com.sgk.model;

import java.util.Date;

public class Search
{
  private int id;
  private String keyword;
  private String ip;
  private String useragent;
  private User user;
  private Date date;

  public int getId()
  {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getKeyword() {
    return this.keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
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

  public User getUser() {
    return this.user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getDate() {
    return this.date;
  }

  public void setDate(Date date) {
    this.date = date;
  }
}
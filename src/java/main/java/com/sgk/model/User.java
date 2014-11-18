package com.sgk.model;

public class User
{
  private int id;
  private String username;
  private String password;
  private String lastLoginIp;
  private String lastLoginUserAgent;
  private String keeplogin;
  private String qq;

  public int getId()
  {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
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

  public String getLastLoginIp() {
    return this.lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public String getLastLoginUserAgent() {
    return this.lastLoginUserAgent;
  }

  public void setLastLoginUserAgent(String lastLoginUserAgent) {
    this.lastLoginUserAgent = lastLoginUserAgent;
  }

  public String getQq() {
    return this.qq;
  }

  public void setQq(String qq) {
    this.qq = qq;
  }

  public String getKeeplogin() {
    return this.keeplogin;
  }

  public void setKeeplogin(String keeplogin) {
    this.keeplogin = keeplogin;
  }
}
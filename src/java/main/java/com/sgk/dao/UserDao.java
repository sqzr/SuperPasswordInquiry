package com.sgk.dao;

import com.sgk.model.User;

public abstract interface UserDao
{
  public abstract User login(String paramString1, String paramString2);

  public abstract void updateLastIpAndLastUserAgent(int paramInt, String paramString1, String paramString2);

  public abstract int getLoginCountByTime(int paramInt, String paramString);

  public abstract void updateKeepLogin(User paramUser, String paramString);

  public abstract User getUserBykeeplogin(String paramString);
}
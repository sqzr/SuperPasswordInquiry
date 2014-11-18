package com.sgk.service;

import com.sgk.model.User;
import java.util.Map;

public abstract interface UserService
{
  public abstract User login(String paramString1, String paramString2, Map<String, String> paramMap);

  public abstract void updateKeepLogin(User paramUser, String paramString);

  public abstract User getUserBykeeplogin(String paramString);
}
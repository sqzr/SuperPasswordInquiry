package com.sgk.service.impl;

import com.sgk.dao.LogDao;
import com.sgk.dao.UserDao;
import com.sgk.model.Log;
import com.sgk.model.User;
import com.sgk.service.UserService;
import java.util.Date;
import java.util.Map;

public class UserServiceImpl
  implements UserService
{
  private UserDao userDao;
  private LogDao logDao;

  public User login(String username, String password, Map<String, String> userEnvironment)
  {
    Log log = new Log();
    log.setType("login");
    log.setIp((String)userEnvironment.get("ip"));
    log.setUseragent((String)userEnvironment.get("useragent"));
    log.setDate(new Date());
    User user = this.userDao.login(username, password);
    log.setContent("username:" + username + "---password:" + password);
    if (user != null)
    {
      log.setResult("true");

      this.logDao.add(log);
      this.userDao.updateLastIpAndLastUserAgent(user.getId(), (String)userEnvironment.get("ip"), (String)userEnvironment.get("useragent"));
      return user;
    }

    log.setResult("false");
    this.logDao.add(log);
    return null;
  }

  public void updateKeepLogin(User user, String keeplogin)
  {
    this.userDao.updateKeepLogin(user, keeplogin);
  }

  public User getUserBykeeplogin(String keeplogin)
  {
    return this.userDao.getUserBykeeplogin(keeplogin);
  }

  public UserDao getUserDao()
  {
    return this.userDao;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }

  public LogDao getLogDao() {
    return this.logDao;
  }

  public void setLogDao(LogDao logDao) {
    this.logDao = logDao;
  }
}
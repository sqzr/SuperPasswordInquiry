package com.sgk.dao.impl;

import com.sgk.dao.UserDao;
import com.sgk.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class UserDaoImpl
  implements UserDao
{
  private SessionFactory sessionFactory;

  public User login(String username, String password)
  {
    Session session = this.sessionFactory.getCurrentSession();

    User user = (User)session.createCriteria(User.class)
      .add(Restrictions.eq("username", username))
      .add(Restrictions.eq("password", password))
      .uniqueResult();
    return user;
  }

  public void updateLastIpAndLastUserAgent(int id, String ip, String useragent)
  {
    Session session = this.sessionFactory.getCurrentSession();
    User user = (User)session.get(User.class, Integer.valueOf(id));
    user.setLastLoginIp(ip);
    user.setLastLoginUserAgent(useragent);
    session.update(user);
  }

  public int getLoginCountByTime(int min, String ip)
  {
    Session session = this.sessionFactory.getCurrentSession();

    return 0;
  }

  public void updateKeepLogin(User user, String value)
  {
    Session session = this.sessionFactory.getCurrentSession();
    user.setKeeplogin(value);
    session.update(user);
  }

  public User getUserBykeeplogin(String keeplogin)
  {
    Session session = this.sessionFactory.getCurrentSession();

    User user = (User)session.createCriteria(User.class)
      .add(Restrictions.eq("keeplogin", keeplogin))
      .uniqueResult();
    return user;
  }

  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
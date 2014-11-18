package com.sgk.dao.impl;

import com.sgk.dao.LogDao;
import com.sgk.model.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LogDaoImpl
  implements LogDao
{
  private SessionFactory sessionFactory;

  public void add(Log log)
  {
    Session session = this.sessionFactory.getCurrentSession();
    session.save(log);
  }

  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
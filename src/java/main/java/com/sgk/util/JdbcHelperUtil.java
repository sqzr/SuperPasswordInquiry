package com.sgk.util;

import java.io.InputStream;
import java.io.PrintStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class JdbcHelperUtil
{
  private static Connection ct = null;

  private static PreparedStatement ps = null;
  private static ResultSet rs = null;
  private static CallableStatement cs = null;

  private static String url = "";
  private static String username = "";
  private static String driver = "";
  private static String password = "";

  private static Properties pp = null;
  private static InputStream fis = null;

  public static Connection getCt()
  {
    return ct;
  }

  public static PreparedStatement getPs() {
    return ps;
  }

  public static ResultSet getRs() {
    return rs;
  }

  public JdbcHelperUtil(InputStream inputStream)
  {
    pp = new Properties();
    try {
      fis = inputStream;
      pp.load(fis);
      url = pp.getProperty("url");
      username = pp.getProperty("username");
      driver = pp.getProperty("driver");
      password = pp.getProperty("password");
      Class.forName(driver);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static Connection getConnection()
  {
    try
    {
      ct = DriverManager.getConnection(url, username, password);
    }
    catch (Exception e) {
    }
    return ct;
  }

  public static ResultSet executeQuery(String sql, String[] parameters)
  {
    try {
      ct = getConnection();
      ps = ct.prepareStatement(sql);
      if ((parameters != null) && (!parameters.equals(""))) {
        for (int i = 0; i < parameters.length; i++) {
          ps.setString(i + 1, parameters[i]);
        }
      }
      rs = ps.executeQuery(); } catch (Exception e) {
      e = 
        e;

      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    }
    finally {
    }
    return rs;
  }

  public static void executeUpdate(String sql, String[] parameters)
  {
    try
    {
      ct = getConnection();
      ps = ct.prepareStatement(sql);

      if (parameters != null) {
        for (int i = 0; i < parameters.length; i++) {
          ps.setString(i + 1, parameters[i]);
        }
      }

      ps.executeUpdate();
    }
    catch (Exception e) {
      e.printStackTrace();

      throw new RuntimeException(e.getMessage());
    }
    finally {
      close(rs, ps, ct);
    }
  }

  public static void executeUpdate2(String[] sql, String[][] parameters)
  {
    try
    {
      ct = getConnection();

      ct.setAutoCommit(false);
      for (int i = 0; i < sql.length; i++) {
        if (parameters[i] != null) {
          ps = ct.prepareStatement(sql[i]);
          for (int j = 0; j < parameters[i].length; j++) {
            ps.setString(j + 1, parameters[i][j]);
          }
          ps.executeUpdate();
        }
      }
      ct.commit();
    }
    catch (Exception e) {
      e.printStackTrace();
      try
      {
        ct.rollback();
      } catch (Exception e1) {
        e1.printStackTrace();
      }

      throw new RuntimeException(e.getMessage());
    } finally {
      close(rs, ps, ct);
    }
  }

  public static ResultSet executeQuery2()
  {
    return null;
  }

  public static void callPro1(String sql, String[] parameters)
  {
    try
    {
      ct = getConnection();
      cs = ct.prepareCall(sql);

      if (parameters != null) {
        for (int i = 0; i < parameters.length; i++) {
          cs.setObject(i + 1, parameters[i]);
        }
      }
      cs.execute();
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new RuntimeException(e.getMessage());
    } finally {
      close(rs, cs, ct);
    }
  }

  public static ResultSet callPro2(String sql, String[] inparameters, String[] outparameters)
  {
    try
    {
      ct = getConnection();
      cs = ct.prepareCall(sql);
      if (inparameters != null)
        for (int i = 0; i < inparameters.length; i++)
          cs.setObject(i + 1, inparameters[i]);
    }
    catch (Exception e) {
      e = 
        e; } finally {
    }return null;
  }

  public static void close(ResultSet rs, Statement ps, Connection ct)
  {
    if (rs != null) {
      try {
        rs.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      rs = null;
    }
    if (ps != null) {
      try {
        ps.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      ps = null;
    }
    if (ct != null) {
      try {
        ct.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
      ct = null;
    }
  }
}
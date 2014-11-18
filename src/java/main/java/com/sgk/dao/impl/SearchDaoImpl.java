package com.sgk.dao.impl;

import com.sgk.dao.SearchDao;
import com.sgk.model.Search;
import com.sgk.util.JdbcHelperUtil;
import com.sgk.util.PropertiesUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.sphx.api.SphinxMatch;
import org.sphx.api.SphinxResult;

public class SearchDaoImpl
  implements SearchDao
{
  PropertiesUtil propertiesUtil = new PropertiesUtil();
  SphinxClient sphinxClient = new SphinxClient();
  private SessionFactory sessionFactory;

  public Map getSearchByKeyword(String keyword)
    throws SphinxException, SQLException
  {
    Map result = new HashMap();

    Map mapResult = new HashMap();

    int count = 0;

    this.propertiesUtil.loadFile("/sphinx.properties", "UTF-8");

    String[] indexs = this.propertiesUtil.getString("indexs").split(",");

    String[] columns = this.propertiesUtil.getString("columns").split(",");
    String host = this.propertiesUtil.getString("host");
    int port = this.propertiesUtil.getInteger("port").intValue();
    int model = this.propertiesUtil.getInteger("matchmode").intValue();
    this.sphinxClient.SetServer(host, port);
    this.sphinxClient.SetMatchMode(model);
    JdbcHelperUtil jdbcHelperUtil = new JdbcHelperUtil(getClass().getResourceAsStream("/dbinfo.properties"));

    for (String index : indexs) {
      SphinxResult sphinxResult = this.sphinxClient.Query(keyword, index);

      if (sphinxResult != null) {
        for (SphinxMatch sphinxMatch : sphinxResult.matches) {
          String[] where = { String.valueOf(sphinxMatch.docId) + "s" };
          ResultSet resultSet = JdbcHelperUtil.executeQuery("select * from `" + index + "` where id = ?", where);

          while (resultSet.next()) {
            Map temp = new HashMap();

            for (String column : columns) {
              temp.put(column, resultSet.getString(column));
            }

            count++;
            mapResult.put(String.valueOf(count), temp);
          }
        }
      }
    }

    if (mapResult.isEmpty()) {
      result.put("status", "error");
    } else {
      result.put("status", "success");
      result.put("rows", mapResult);
      result.put("count", Integer.valueOf(count));
    }
    return result;
  }

  public void addSearchLog(Search search)
  {
    Session session = this.sessionFactory.getCurrentSession();
    session.save(search);
  }

  public SessionFactory getSessionFactory()
  {
    return this.sessionFactory;
  }

  public void setSessionFactory(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }
}
package com.sgk.service.impl;

import com.sgk.dao.SearchDao;
import com.sgk.model.Search;
import com.sgk.model.User;
import com.sgk.service.SearchService;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import org.sphx.api.SphinxException;

public class SearchServiceImpl
  implements SearchService
{
  private SearchDao searchDao;

  public Map getSearchByKeyword(String keyword, Map<String, Object> userEnvironment)
    throws SphinxException, SQLException
  {
    Search search = new Search();
    search.setKeyword(keyword);
    User user = (User)userEnvironment.get("user");
    search.setUser((User)userEnvironment.get("user"));
    search.setIp((String)userEnvironment.get("ip"));
    search.setUseragent((String)userEnvironment.get("useragent"));
    search.setDate(new Date());
    this.searchDao.addSearchLog(search);
    return this.searchDao.getSearchByKeyword(keyword);
  }

  public SearchDao getSearchDao()
  {
    return this.searchDao;
  }

  public void setSearchDao(SearchDao searchDao) {
    this.searchDao = searchDao;
  }
}
package com.sgk.dao;

import com.sgk.model.Search;
import java.sql.SQLException;
import java.util.Map;
import org.sphx.api.SphinxException;

public abstract interface SearchDao
{
  public abstract Map getSearchByKeyword(String paramString)
    throws SphinxException, SQLException;

  public abstract void addSearchLog(Search paramSearch);
}
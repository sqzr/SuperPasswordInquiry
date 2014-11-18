package com.sgk.service;

import java.sql.SQLException;
import java.util.Map;
import org.sphx.api.SphinxException;

public abstract interface SearchService
{
  public abstract Map getSearchByKeyword(String paramString, Map<String, Object> paramMap)
    throws SphinxException, SQLException;
}
package com.sgk.util;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class RequestInfoUtil
{
  public static String getIpAddr(HttpServletRequest request)
  {
    String ip = request.getHeader("X-Real-IP");
    if ((null != ip) && (!"".equals(ip.trim())) && 
      (!"unknown"
      .equalsIgnoreCase(ip)))
    {
      return ip;
    }
    ip = request.getHeader("X-Forwarded-For");
    if ((null != ip) && (!"".equals(ip.trim())) && 
      (!"unknown"
      .equalsIgnoreCase(ip)))
    {
      int index = ip.indexOf(',');
      if (index != -1) {
        return ip.substring(0, index);
      }
      return ip;
    }

    return request.getRemoteAddr();
  }

  public static String getUserAgent(HttpServletRequest request) {
    return request.getHeader("User-Agent");
  }

  public static Map<String, String> getIpAddrAndUserAgent(HttpServletRequest request)
  {
    Map map = new HashMap();
    map.put("ip", getIpAddr(request));
    map.put("useragent", request.getHeader("User-Agent"));
    return map;
  }
}
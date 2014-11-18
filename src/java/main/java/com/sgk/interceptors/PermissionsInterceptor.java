package com.sgk.interceptors;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sgk.model.User;
import com.sgk.service.UserService;
import com.sgk.util.CookieUtil;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PermissionsInterceptor
  implements Interceptor
{
  public void destroy()
  {
  }

  public void init()
  {
  }

  public String intercept(ActionInvocation actionInvocation)
    throws Exception
  {
    ActionContext ctx = ActionContext.getContext();
    Map session = ctx.getSession();
    HttpServletRequest request = ServletActionContext.getRequest();
    Cookie cookie = CookieUtil.getCookie(request, "keeplogin");
    if (session.get("user") == null)
    {
      if (cookie == null)
      {
        return "notLogon";
      }

      ApplicationContext appctx = new ClassPathXmlApplicationContext("spring-config.xml");
      UserService userService = (UserService)appctx.getBean("userService");
      User user = userService.getUserBykeeplogin(cookie.getValue());
      if (user.equals(null)) {
        return "notLogon";
      }
      session.put("user", user);
    }

    return actionInvocation.invoke();
  }
}
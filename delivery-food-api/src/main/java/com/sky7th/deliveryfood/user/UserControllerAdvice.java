package com.sky7th.deliveryfood.user;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserControllerAdvice {

  @ModelAttribute("ipAddress")
  public String getIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null) {
      ip = request.getRemoteAddr();
    }

    return ip;
  }
}

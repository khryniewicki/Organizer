package com.khryniewicki.organizer.main_content.Utills;

import com.khryniewicki.organizer.main_content.model.User;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UtillClass {
    public static User getLoggedInUser(){
        HttpSession session = getCurrentHttpRequest().getSession(false);
        return (User) session.getAttribute("appUser");
    }
    public static HttpServletRequest getCurrentHttpRequest(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
            return request;
        }
        return null;
    }
}

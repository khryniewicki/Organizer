package com.khryniewicki.organizer.registration_login_logout.config;

import com.khryniewicki.organizer.main_content.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse httpServletResponse,
                       Authentication authentication) {
        HttpSession session = request.getSession(false);
        User user = (User)session.getAttribute("appUser");

    }
}


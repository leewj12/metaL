package com.metalearning.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String useremail = authentication.getName();

        logger.info("User {} logged in at {}", useremail, LocalDateTime.now());

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));
        boolean isManager = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_MANAGER"));
        boolean isInstructor = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_INSTRUCTOR"));
        boolean isStudent = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_STUDENT"));

        if (isAdmin) {
            response.sendRedirect("/admin");  // 관리자 대시보드로 리디렉션
        } else if (isManager) {
            response.sendRedirect("/manager");  // 매니저 대시보드로 리디렉션
        } else if (isInstructor) {
            response.sendRedirect("/instructor");  // 인스트럭터 대시보드로 리디렉션
        } else {
            response.sendRedirect("/");  // 기본 홈으로 리디렉션
        }
    }
}

package kosmo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String useremail = authentication.getName();  // 로그인한 사용자의 이메일

        // 로그인 시간 로그 기록
        logger.info("이건 로그인 ====== User {} logged in at {}", useremail, LocalDateTime.now());

        // "ROLE_ADMIN" 권한을 가진 사용자는 관리자 대시보드로 리디렉션
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"));

        if (isAdmin) {
            response.sendRedirect("/admin");  // 관리자는 '/admin'으로 리디렉션
        } else {
            response.sendRedirect("/");  // 일반 사용자는 홈으로 리디렉션
        }
    }
}

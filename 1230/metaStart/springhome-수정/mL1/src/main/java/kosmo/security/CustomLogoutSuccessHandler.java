package kosmo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication != null) {
            String useremail = authentication.getName();  // 로그아웃한 사용자의 이메일

            // 로그아웃 시간 로그 기록
            logger.info("이건 그냥 로그아웃 ============ User {} logged out at {}", useremail, LocalDateTime.now());
        }

        // 로그아웃 후 리디렉션 경로
        response.sendRedirect("/");
    }
}

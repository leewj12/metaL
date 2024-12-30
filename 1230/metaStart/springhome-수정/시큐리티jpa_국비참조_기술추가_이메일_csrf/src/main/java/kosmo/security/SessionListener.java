package kosmo.security;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        // 세션 생성 시
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            String username = authentication.getName();  // 로그인한 사용자
            String sessionId = se.getSession().getId();  // 세션 ID
            String ipAddress = se.getSession().getAttribute("IP_ADDRESS").toString();
            String userAgent = se.getSession().getAttribute("USER_AGENT").toString();

            // 세션 만료/로그아웃 시 로그 기록
            logger.info("User {} session expired/logged out at {} with sessionId: {} from IP: {} with User-Agent: {}",
                    username, LocalDateTime.now(), sessionId, ipAddress, userAgent);
        }
    }
}

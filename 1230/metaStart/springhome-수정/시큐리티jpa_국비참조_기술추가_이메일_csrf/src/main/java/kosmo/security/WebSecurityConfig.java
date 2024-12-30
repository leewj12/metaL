package kosmo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private UserDetailsService userDetailService;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    private static final String[] WHITE_LIST = { "/", "/login", "/signup", "/home", "/public/**", "/static/**", "/mypage/**", "/api/**" };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 요청 URL에 대한 권한 설정
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()  // 화이트리스트는 모두 허용
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/auth/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest().authenticated()
                )
                // 로그인 설정
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                )
                // 로그아웃 설정
                .logout(logout -> logout
                        .logoutSuccessHandler(customLogoutSuccessHandler)
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                )
                // 세션 관리 설정
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                        .maximumSessions(1)  // 최대 세션 수 1로 제한
                        .maxSessionsPreventsLogin(true)  // 기존 세션이 있을 경우 새로운 로그인 차단
                        .expiredUrl("/login")  // 세션 만료 시 리디렉션할 URL 설정
                );

        return http.build();
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();  // 비밀번호 암호화에 사용할 BCryptPasswordEncoder Bean 설정
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailService);  // UserDetailsService 설정
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());  // 비밀번호 인코더 설정

        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(authProvider)  // AuthenticationProvider 설정
                .build();  // AuthenticationManager 반환
    }
}

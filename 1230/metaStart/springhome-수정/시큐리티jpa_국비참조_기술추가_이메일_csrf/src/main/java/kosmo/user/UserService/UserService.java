package kosmo.user.UserService;

import kosmo.user.UserDTO.EmailVerificationDTO;
import kosmo.user.dto.SignUpDTO;

public interface UserService {
    // 이메일 중복 확인
    boolean isEmailAvailable(String email);

    // 회원가입 처리
    boolean usersave(SignUpDTO signUpDTO);

    // 이메일 인증 코드 발송 (이메일을 직접 받도록 수정)
    boolean sendVerificationEmail(String email);

    // 이메일 인증 코드 검증 (EmailVerificationDTO를 받도록 수정)
    boolean verifyEmailCode(EmailVerificationDTO emailVerificationDTO);
}

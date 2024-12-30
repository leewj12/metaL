package kosmo.user.UserService;

import kosmo.user.UserDTO.EmailVerificationDTO;
import kosmo.user.UserEntity.UserEntity;
import kosmo.user.UserRepository.UserRepository;
import kosmo.user.dto.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final kosmo.user.UserService.EmailService emailService;  // EmailService 주입

    // 이메일 중복 확인
    @Override
    public boolean isEmailAvailable(String email) {
        return !userRepository.findByUseremail(email).isPresent(); // 이메일 중복 체크
    }

    // 이메일 인증 코드 검증
    @Override
    public boolean verifyEmailCode(EmailVerificationDTO emailVerificationDTO) {
        String email = emailVerificationDTO.getUseremail();
        String verificationCode = emailVerificationDTO.getVerificationCode();

        // 인증 코드 확인 로직
        if (email == null || verificationCode == null) {
            return false;  // null 체크
        }

        // EmailService의 verifyEmailCode 메서드를 호출
        return emailService.verifyEmailCode(email, verificationCode);  // 이메일과 인증 코드 비교
    }

    // 회원가입 처리
    @Override
    public boolean usersave(SignUpDTO signUpDTO) {
        if (userRepository.findByUseremail(signUpDTO.getUseremail()).isPresent()) {
            return false;  // 이미 사용 중인 이메일이라면 회원가입 실패
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());

        // UserEntity 객체 생성 후 저장
        UserEntity userEntity = UserEntity.builder()
                .useremail(signUpDTO.getUseremail())
                .password(encodedPassword)
                .name(signUpDTO.getName())
                .usergender(signUpDTO.getUsergender())
                .userbirth(signUpDTO.getUserbirth())
                .userPhoneNumber(signUpDTO.getUserPhoneNumber())
                .role("USER")
                .build();

        userRepository.save(userEntity);
        return true;  // 저장 성공
    }

    // 이메일 인증번호 발송 처리
    @Override
    public boolean sendVerificationEmail(String email) {
        if (userRepository.findByUseremail(email).isPresent()) {
            return false;  // 이미 사용 중인 이메일이라면 false 반환
        }

        // 인증번호 생성 및 이메일 전송
        String verificationCode = emailService.generateVerificationCode();
        emailService.sendVerificationEmail(email, verificationCode);
        return true;  // 인증 이메일이 전송되었으면 true 반환
    }
}

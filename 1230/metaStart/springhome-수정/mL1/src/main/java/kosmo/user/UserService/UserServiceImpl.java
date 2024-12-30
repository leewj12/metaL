package kosmo.user.UserService;

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

    // 회원가입 처리
    public boolean usersave(SignUpDTO signUpDTO) {
        try {
            // 이메일 중복 확인
            if (userRepository.findByUseremail(signUpDTO.getUseremail()).isPresent()) {
                return false;  // 이미 사용 중인 이메일이면 저장하지 않음
            }

            // 비밀번호 암호화
            String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());

            // UserEntity 생성
            UserEntity userEntity = UserEntity.builder()
                    .useremail(signUpDTO.getUseremail())
                    .password(encodedPassword)
                    .name(signUpDTO.getName())
                    .usergender(signUpDTO.getUsergender())
                    .userbirth(signUpDTO.getUserbirth())
                    .userPhoneNumber(signUpDTO.getUserPhoneNumber())  // 전화번호 저장
                    .role("ADMIN")  // 기본값으로 "USER" 역할
                    .build();

            // DB에 저장
            userRepository.save(userEntity);

            return true;  // 저장이 성공하면 true 반환
        } catch (Exception e) {
            // 예외 발생 시 로그를 기록하거나, 예외 메시지를 반환
            System.err.println("회원가입 처리 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
            return false;  // 오류 발생 시 false 반환
        }
    }
}
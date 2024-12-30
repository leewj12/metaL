package kosmo.user.UserController;

import kosmo.Utility.ResponseMagseg;
import kosmo.user.UserDTO.EmailVerificationDTO;
import kosmo.user.UserService.EmailService;
import kosmo.user.UserService.UserService;
import kosmo.user.dto.SignUpDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;
    private final EmailService emailService;

    @PostMapping("/check-email")
    public ResponseEntity<ResponseMagseg> checkEmail(@RequestBody SignUpDTO signUpDTO) {
        boolean isEmailAvailable = userService.isEmailAvailable(signUpDTO.getUseremail());
        ResponseMagseg responseMagseg;
        if (isEmailAvailable) {
            responseMagseg = new ResponseMagseg("ok", "사용 가능한 이메일입니다.");
            return ResponseEntity.ok(responseMagseg);
        } else {
            responseMagseg = new ResponseMagseg("fail", "이미 사용 중인 이메일입니다.");
            return ResponseEntity.status(400).body(responseMagseg);
        }
    }

    @PostMapping("/send-verification-email")
    public ResponseEntity<ResponseMagseg> sendVerificationEmail(@RequestBody EmailVerificationDTO emailVerificationDTO) {
        String email = emailVerificationDTO.getUseremail();

        if (email == null || email.trim().isEmpty()) {
            ResponseMagseg responseMagseg = new ResponseMagseg("fail", "이메일 주소가 비어 있습니다.");
            return ResponseEntity.status(400).body(responseMagseg);
        }

        boolean isEmailAvailable = userService.isEmailAvailable(email);
        if (!isEmailAvailable) {
            ResponseMagseg responseMagseg = new ResponseMagseg("fail", "이미 사용 중인 이메일입니다.");
            return ResponseEntity.status(400).body(responseMagseg);
        }

        try {
            String verificationCode = emailService.generateVerificationCode();
            emailService.sendVerificationEmail(email, verificationCode);  // 이메일 발송
        } catch (Exception e) {
            ResponseMagseg responseMagseg = new ResponseMagseg("fail", "인증 이메일 전송 실패: " + e.getMessage());
            return ResponseEntity.status(500).body(responseMagseg);  // 500 오류로 전송 실패 메시지 전달
        }

        ResponseMagseg responseMagseg = new ResponseMagseg("ok", "인증 이메일이 전송되었습니다.");
        return ResponseEntity.ok(responseMagseg);
    }

    @PostMapping("/verify-email")
    public ResponseEntity<ResponseMagseg> verifyEmail(@RequestBody EmailVerificationDTO emailVerificationDTO) {
        boolean isVerified = userService.verifyEmailCode(emailVerificationDTO);
        ResponseMagseg responseMagseg;
        if (isVerified) {
            responseMagseg = new ResponseMagseg("ok", "이메일 인증이 완료되었습니다.");
            return ResponseEntity.ok(responseMagseg);
        } else {
            responseMagseg = new ResponseMagseg("fail", "인증 코드가 올바르지 않습니다.");
            return ResponseEntity.status(400).body(responseMagseg);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ResponseMagseg> signUp(@RequestBody SignUpDTO signUpDTO) {
        boolean isSaved = userService.usersave(signUpDTO);
        ResponseMagseg responseMagseg;
        if (isSaved) {
            responseMagseg = new ResponseMagseg("ok", "회원가입이 완료되었습니다.");
            return ResponseEntity.ok(responseMagseg);
        } else {
            responseMagseg = new ResponseMagseg("fail", "회원가입에 실패했습니다. 인증을 확인해주세요.");
            return ResponseEntity.status(400).body(responseMagseg);
        }
    }
}

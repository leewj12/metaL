package kosmo.user.UserController;

import kosmo.user.UserService.UserService;
import kosmo.user.dto.SignUpDTO;
import kosmo.Utility.ResponseMagseg;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    // 회원가입 처리
    @PostMapping("/signup")
    public ResponseEntity<ResponseMagseg> signUp(@RequestBody SignUpDTO signUpDTO) {
        boolean isSaved = userService.usersave(signUpDTO);

        ResponseMagseg responseMagseg;
        if (isSaved) {
            // 회원가입 성공
            responseMagseg = new ResponseMagseg("ok", "회원가입이 완료되었습니다.");
            return ResponseEntity.ok(responseMagseg);  // 200 OK 상태로 응답
        } else {
            // 회원가입 실패
            responseMagseg = new ResponseMagseg("fail", "이미 사용 중인 이메일입니다.");
            return ResponseEntity.status(400).body(responseMagseg);  // 400 Bad Request 상태로 응답
        }
    }

}

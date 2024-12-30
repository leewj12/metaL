package kosmo.test.testcontroller;

import kosmo.user.UserEntity.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/videotest")
    public String test() {
        return "/test/videotest";  // /test/videotest.jsp 또는 videotest.html
    }

    @GetMapping("/videoview")
    public String videoView() {
        return "/test/videoview";  // /test/videoview.jsp 또는 videoview.html
    }

    @GetMapping("/userId")
    public String getUserId(@AuthenticationPrincipal UserEntity user, Model model) {
        // 로그인된 사용자의 프라이머리 키를 가져옵니다.
        Long userId = user.getUserid();

        // 모델에 userId를 추가하여 뷰에 전달
        model.addAttribute("userId", userId);

        // userId를 보여주는 JSP 또는 HTML 뷰를 반환
        return "/test/userIdView";  // /WEB-INF/views/userIdView.jsp 또는 userIdView.html
    }


    // 사용자가 권한에 따라 리디렉션하는 메서드
    // 사용자가 권한에 따라 리디렉션하는 메서드 // 화이트리스트 열어줘야함
    @GetMapping("/hand")
    public String handlerPage(@AuthenticationPrincipal UserEntity user) {
        // 사용자의 권한을 확인합니다.
        if (user != null) {
            // 사용자의 역할에 따라 리디렉션 처리
            if ("ADMIN".equals(user.getRole())) {
                return "redirect:/admin";  // 관리자 페이지로 리디렉션
            } else if ("MANAGER".equals(user.getRole())) {
                return "redirect:/manager";  // 매니저 페이지로 리디렉션
            } else if ("USER".equals(user.getRole())) {
                return "redirect:/user";  // 사용자 페이지로 리디렉션
            }
        }

        // 권한이 없거나 비로그인 상태일 경우 사용자 페이지로 리디렉션
        return "redirect:/login";
    }







}

package kosmo.Utility;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 이 클래스는 Spring MVC에서 요청을 처리하는 컨트롤러임을 나타냄
public class AccessDeniedController {

    // "/accessDenied" 경로로 GET 요청이 들어오면 이 메서드가 실행됨
    @GetMapping("/accessDenied")
    public String accessDenied() {
        // "/Utility/accessDenied" 뷰를 반환 (이 뷰는 "src/main/resources/templates/Utility/accessDenied.html" 파일을 가리킴)
        return "Utility/accessDenied";  // 뷰 이름 반환
    }


    @GetMapping("/nologin")
    public String nologin(Model model){
        String msg = "로그인이 필요합니다!";
        String loc = "/login";
        model.addAttribute("msg",msg);
        model.addAttribute("loc",loc);

        return "/Utility/Utility";
    }


}

package kosmo.course.coursecontroller;

import kosmo.course.coursedto.NationalSessionCourseDTO;
import kosmo.course.courseservice.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/nationalcourse")
    public String nationalcourse() {
        return "/course/nationalcourse";  // This refers to the view (JSP or HTML) to be rendered
    }

    @GetMapping("/session")
    public String session(){
        return "/course/session";
    }

    @GetMapping("/sessionview")
    public String sessionview(){
        return "/course/sessionview";
    }


    @GetMapping("/editSession")
    public String editSession(@RequestParam("sessionId") Long sessionId, Model model) {
        // sessionId로 세션을 편집하기 위해 서비스 호출
        Optional<NationalSessionCourseDTO> editSession = courseService.editSession(sessionId);

        // 세션이 존재하는지 확인
        if (editSession.isPresent()) {
            // 세션 정보를 모델에 추가
            model.addAttribute("editSession", editSession.get());
        } else {
            // 세션이 존재하지 않으면, 에러 처리나 알림 등을 수행할 수 있습니다.
            model.addAttribute("error", "Session not found");
        }

        return "/course/editSession"; // editSession.html로 이동
    }


    // Controller에서 DTO를 받아서 처리
    @PostMapping("/editSession")
    public String editSession(@ModelAttribute NationalSessionCourseDTO editSessionDTO, Model model) {
        // editSessionDTO에는 클라이언트가 수정한 세션 정보가 담겨있습니다.

        // 서비스에서 세션 정보 업데이트
        boolean updateSuccess = courseService.updateSession(editSessionDTO);

        // 업데이트 성공 여부에 따라 모델에 메시지 추가
        if (updateSuccess) {
            model.addAttribute("message", "세션 정보가 성공적으로 수정되었습니다.");
        } else {
            model.addAttribute("error", "세션 수정에 실패했습니다.");
        }

        // 수정된 세션 정보 페이지로 리다이렉션
        return "redirect:/sessionview";
    }





}

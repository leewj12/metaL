package kosmo.course.coursecontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

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

}

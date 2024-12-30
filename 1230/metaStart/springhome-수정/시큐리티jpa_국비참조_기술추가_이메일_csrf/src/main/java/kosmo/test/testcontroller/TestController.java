package kosmo.test.testcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/videotest")
    public String test(){
        return "/test/videotest";
    }

   @GetMapping("videoview")
   public String vdieoview(){
        return "/test/videoview";
   }

    
}

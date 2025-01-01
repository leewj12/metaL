package com.metalearning.admin.admincontroller;

import com.metalearning.KDT.KDTdto.KDTCourseDto;
import com.metalearning.KDT.KDTdto.KDTSessionDto;
import com.metalearning.KDT.KDTservice.KdtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j

public class AdminController {

    private final KdtService kdtService;


    //국비과정 등록하기
    @GetMapping("/admin/KDT/course")
    public String getkdtcourse(){
        return "/admin/KDT/course";
    }

    //국비과정 등록하기
    @PostMapping("/admin/KDT/course")
    public String postkdtcourse(KDTCourseDto kdtCourseDto, Model model){


        log.info("데이터 오는지 확인해보자===============================-{}",kdtCourseDto);


        int result = kdtService.kdtcoursesave(kdtCourseDto);

        switch (result) {
            case 1: // 성공
                model.addAttribute("msg", "국비과정이 등록이 완료되었습니다!");
                model.addAttribute("loc", "/");  // 홈으로 리디렉션
                break;
            case 2: // 이미 있음
                model.addAttribute("msg", "이미 존재하는 국비과정입니다!");
                model.addAttribute("loc", "/admin/KDT/course");  // 다시 입력 페이지로
                break;
            default: // 실패
                model.addAttribute("msg", "국비과정 등록을 실패했습니다!");
                model.addAttribute("loc", "/admin/KDT/course");  // 다시 입력 페이지로
                break;
        }

        return "/utility/message";
    }

    //회차등록하기
    @GetMapping("/admin/KDT/session")
    public String getkdtsession(Model model) {
        List<KDTCourseDto> courseall = kdtService.courseall();
        model.addAttribute("courseall", courseall);  // 데이터를 모델에 추가하여 뷰로 전달
        return "/admin/KDT/session";  // 해당 뷰로 이동

    }

    //회차등록하기
    @PostMapping("/admin/KDT/session")
    public String postkdtsession(KDTSessionDto kdtSessionDto, Model model){


        log.info("데이터 오는지 확인해보자===============================-{}",kdtSessionDto);

        int result = kdtService.kdtsessionsave(kdtSessionDto);

        switch (result) {
            case 1: // 성공
                model.addAttribute("msg", "국비과정 회차 등록이 완료되었습니다!");
                model.addAttribute("loc", "/");  // 홈으로 리디렉션
                break;
            case 2: // 이미 있음
                model.addAttribute("msg", "이미 존재하는 회차과정입니다!");
                model.addAttribute("loc", "/admin/KDT/session");  // 다시 입력 페이지로
                break;
            default: // 실패
                model.addAttribute("msg", "국비과정회차 등록을 실패했습니다!");
                model.addAttribute("loc", "/admin/KDT/session");  // 다시 입력 페이지로
                break;
        }

        return "/utility/message";
    }

    @GetMapping("/admin/mypage")
    public String adminmypage(){
        return "/users/detail";
    }


    //어드민 대시보드 보려고 테스트한거임 지워야함
    @GetMapping("/admin/test")
        public String admintest(){
            return "/admin/KDT/admindashboard";
    }



}
package com.metalearning.admin.admincontroller;

import com.metalearning.KDT.KDTservice.KdtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/KDT/session")
public class AdminRestController {

    private final KdtService kdtService;


    @GetMapping("/getSessionNum")
    public ResponseEntity<Map<String, Object>> getSessionNum(@RequestParam Long courseId) {

        // 세션 번호와 과정명을 가져오는 서비스 호출
        Map<String, Object> sessionInfo = kdtService.getSessionNumAndCourseTitleByCourseId(courseId);

        // 세션 번호 증가
        int sessionNum = (int) sessionInfo.get("sessionNum");
        String courseTitle = (String) sessionInfo.get("courseTitle");
        int nextSessionNumber = sessionNum + 1;

        // 응답 데이터 준비
        Map<String, Object> response = new HashMap<>();
        response.put("sessionNum", nextSessionNumber); // 세션 번호
        response.put("courseTitle", courseTitle);     // 과정명

        // 응답 반환
        return ResponseEntity.ok(response);
    }

}

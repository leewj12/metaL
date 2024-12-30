package kosmo.course.coursecontroller;

import kosmo.Utility.ResponseMagseg;
import kosmo.course.coursedto.NationalCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseDTO;
import kosmo.course.courseservice.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CourseRestController {
    private final CourseService courseService;

    @PostMapping("/api/NationalCourse")
    public ResponseEntity<?> registerNationalCourse(@RequestBody NationalCourseDTO nationalCourseDTO) {
        try {
            boolean coursesave = courseService.NationalCourse(nationalCourseDTO);

            // 성공적으로 등록된 경우
            if (coursesave) {
                ResponseMagseg response = new ResponseMagseg("success", "국비 과정이 성공적으로 등록되었습니다.");
                return ResponseEntity.ok(response);  // ResponseMagseg 객체를 OK 응답으로 반환
            } else {
                // 중복된 과정인 경우
                ResponseMagseg response = new ResponseMagseg("failure", "이미 등록된 과정입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 중복 시 BAD_REQUEST 응답
            }

        } catch (Exception e) {
            // 예외 발생 시
            ResponseMagseg response = new ResponseMagseg("error", "등록 중 오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
    @GetMapping("/api/NationalCourseList")
    public ResponseEntity<List<NationalCourseDTO>> getNationalCourseList() {
        try {
            // Service를 통해 모든 국비 과정을 조회
            List<NationalCourseDTO> courseList = courseService.getAllNationalCourses();
            return ResponseEntity.ok(courseList); // JSON 형식으로 반환
        } catch (Exception e) {
            // 예외 발생 시 에러 메시지 반환
            return ResponseEntity.status(500).body(null);
        }
    }

    @GetMapping("/api/NationalCourseSessions/{courseId}")
    public ResponseEntity<Long> getNextSession(@PathVariable Long courseId) {
        // 해당 과정의 마지막 회차를 조회 (회차가 없으면 0)
        Long lastSessionNumber = courseService.getLastSessionNumberByCourseId(courseId);

        // 회차가 없으면 0을 반환, 있으면 +1하여 반환
        Long nextSessionNumber = lastSessionNumber + 1;

        return ResponseEntity.ok(nextSessionNumber); // 회차 번호를 반환
    }


    @PostMapping("/api/NationalSessionCourse")
    public ResponseEntity<?> createNationalSessionCourse(@RequestBody NationalSessionCourseDTO NationalSessionCoursedto) {
        log.info("데이터 확인====================================={}",NationalSessionCoursedto);

        try {
            // 서비스에서 등록 처리
            boolean coursesave = courseService.NationalSessionCourse(NationalSessionCoursedto);

            // 성공적으로 등록된 경우
            if (coursesave) {
                ResponseMagseg response = new ResponseMagseg("success", "국비 과정이 성공적으로 등록되었습니다.");
                return ResponseEntity.ok(response);  // ResponseMagseg 객체를 OK 응답으로 반환
            } else {
                // 중복된 과정인 경우
                ResponseMagseg response = new ResponseMagseg("failure", "이미 등록된 과정입니다.");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response); // 중복 시 BAD_REQUEST 응답
            }
        } catch (Exception e) {
            // 예외 처리
            ResponseMagseg response = new ResponseMagseg("error", "오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); // 서버 오류 응답
        }
    }


    // 특정 국비 과정의 회차 정보를 조회
    @GetMapping("/api/NationalCourseSessionsview/{courseId}")
    public ResponseEntity<?> getNationalCourseSessions(@PathVariable Long courseId) {
        try {
            // 서비스에서 해당 국비 과정의 회차 정보 조회
            List<NationalSessionCourseDTO> sessionList = courseService.getSessionsByCourseId(courseId);

            // 회차 정보가 없으면 실패 메시지와 함께 OK 응답 반환
            if (sessionList.isEmpty()) {
                ResponseMagseg response = new ResponseMagseg("failure", "회차 정보가 없습니다.");
                return ResponseEntity.status(HttpStatus.OK).body(response); // 200 OK 응답
            }

            // 회차 정보가 있으면 성공 메시지와 함께 회차 리스트 반환
            return ResponseEntity.status(HttpStatus.OK).body(sessionList); // 200 OK 응답

        } catch (Exception e) {
            // 예외 발생 시 에러 메시지와 함께 OK 응답 반환
            ResponseMagseg response = new ResponseMagseg("error", "오류가 발생했습니다: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body(response); // 200 OK 응답
        }
    }


}

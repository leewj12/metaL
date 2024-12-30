package kosmo.course.coursedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 포함하는 생성자
public class NationalCourseDTO {

    private Long nationalCourseId;  // 국비 과정 ID
    private String courseName;      // 과정명
    private String courseStatus;    // 수강 상태

}

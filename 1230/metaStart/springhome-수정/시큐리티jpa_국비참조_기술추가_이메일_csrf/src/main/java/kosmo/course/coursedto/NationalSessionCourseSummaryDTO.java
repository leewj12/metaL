package kosmo.course.coursedto;

import kosmo.course.courseentity.NationalSessionCourse;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NationalSessionCourseSummaryDTO {

    private Long nationalSessionCourseId;
    private String sessionName;
    private Long sessionNumber;
    private LocalDate sessionStartDate;
    private LocalDate sessionEndDate;


    public static NationalSessionCourseSummaryDTO fromEntity(NationalSessionCourse session) {
        return NationalSessionCourseSummaryDTO.builder()
                .nationalSessionCourseId(session.getNationalSessionCourseId())  // 회차 ID
                .sessionName(session.getSessionName())  // 회차 이름
                .sessionNumber(session.getSessionNumber())  // 회차 번호
                .sessionStartDate(session.getSessionStartDate())  // 회차 시작 날짜
                .sessionEndDate(session.getSessionEndDate())  // 회차 종료 날짜
                .build();
    }


}

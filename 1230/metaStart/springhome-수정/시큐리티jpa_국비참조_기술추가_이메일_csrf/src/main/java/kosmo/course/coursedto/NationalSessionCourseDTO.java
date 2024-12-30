package kosmo.course.coursedto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class NationalSessionCourseDTO {

    private Long nationalSessionCourseId; // 국비 과정 회차 ID
    private Long nationalCourseId; // 국비 과정 ID (국비 과정과 연관)
    private String sessionName; // 국비 과정 회차명
    private Long sessionNumber;
    private String sessionDescription; // 국비 과정 설명
    private LocalDate sessionStartDate; // 국비 시작일
    private LocalDate sessionEndDate; // 국비 종료일
    private String curriculum; // 국비 커리큘럼
    private String category; // 국비 카테고리
    private Integer maxCapacity; // 국비 과정 최대 인원
    private String isOnline; // 국비 과정 온라인 여부 (문자열: "Online" 또는 "Offline")
}

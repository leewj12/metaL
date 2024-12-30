package kosmo.course.courseentity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "National_Session_Course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@ToString
public class NationalSessionCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NationalSessionCourse_id")
    private Long nationalSessionCourseId; // 기본 키

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NationalCourse_id", referencedColumnName = "NationalCourse_id", nullable = false)
    private NationalCourse nationalCourse;

    @Column(name = "session_name", nullable = false)
    private String sessionName; // 국비 과정 회차명

    @Column(name = "session_number", nullable = false)
    private Long sessionNumber; // 국비 과정 회차

    @Column(name = "session_description")
    private String sessionDescription; // 국비 과정 설명

    @Column(name = "session_start_date", nullable = false)
    private LocalDate sessionStartDate; // 국비 시작일

    @Column(name = "session_end_date", nullable = false)
    private LocalDate sessionEndDate; // 국비 종료일

    @Column(name = "curriculum")
    private String curriculum; // 국비 커리큘럼

    @Column(name = "category")
    private String category; // 국비 카테고리

    @Column(name = "max_capacity")
    private Integer maxCapacity; // 국비 과정 최대 인원

    @Column(name = "is_online")
    private String isOnline; // "온라인" 또는 "오프라인"
}

package kosmo.course.courseentity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "National_Course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
@ToString
public class NationalCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NationalCourse_id")
    private Long nationalCourseId;


    @Column(name = "course_name", nullable = false) // 과정명
    private String courseName;

    @Column(name = "course_status", nullable = false) // 수강 상태
    private String courseStatus;  // 수강 상태를 문자열로 저장

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성 날짜

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 마지막 수정 날짜
}

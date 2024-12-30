package kosmo.test.testentity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "test_video")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "video_id")
    private Long videoId; // 기본 키

    @Column(name = "video_uuid", nullable = false, unique = true)
    private String videoUuid; // 고유 식별자

    @Column(name = "video_original_file_name", nullable = false)
    private String videoOriginalFileName; // 원본 파일 이름

    @Column(name = "video_size", nullable = false)
    private String videoSize; // 파일 크기

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // 생성 날짜

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt; // 마지막 수정 날짜
}

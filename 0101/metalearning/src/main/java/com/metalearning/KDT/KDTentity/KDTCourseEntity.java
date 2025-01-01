package com.metalearning.KDT.KDTentity;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "KDT_course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@EntityListeners(AuditingEntityListener.class)

public class KDTCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KDT_course_id")
    private Long KDTCourseId;

    @Column(name = "KDT_course_title", nullable = false) // 과정명
    private String KDTCourseTitle;

    @Column(name = "KDT_course_status", nullable = false)
    private Boolean KDTCourseStatus;

    @Column(name = "KDT_course_type", nullable = false)
    private String KDTCourseType;

    @CreatedDate
    @Column(name = "KDT_course_created_at", updatable = false)
    private LocalDateTime KDTCourseCreatedAt; // 생성 날짜

    @LastModifiedDate
    @Column(name = "KDT_course_updated_at")
    private LocalDateTime KDTCourseUpdatedAt; // 마지막 수정 날짜


}

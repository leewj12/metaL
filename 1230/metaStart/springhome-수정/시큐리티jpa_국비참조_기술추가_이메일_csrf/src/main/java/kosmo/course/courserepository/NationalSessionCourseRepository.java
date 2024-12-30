package kosmo.course.courserepository;

import kosmo.course.courseentity.NationalSessionCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Spring Data JPA를 이용한 리포지토리 인터페이스 정의
@Repository
public interface NationalSessionCourseRepository extends JpaRepository<NationalSessionCourse, Long> {

    // NationalCourseId를 정확히 반영하여 수정
    Optional<NationalSessionCourse> findTopByNationalCourse_nationalCourseIdOrderBySessionNumberDesc(Long nationalCourseId);

    // NationalCourse와 sessionName을 기준으로 중복 체크하는 메서드
    boolean existsByNationalCourseNationalCourseIdAndSessionName(Long nationalCourseId, String sessionName);


    List<NationalSessionCourse> findByNationalCourse_NationalCourseId(Long nationalCourseId);

}



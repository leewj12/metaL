package kosmo.course.courserepository;

import kosmo.course.courseentity.NationalCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NationalCourseRepository extends JpaRepository<NationalCourse, Long> {
    boolean existsByCourseName(String courseName);
    // 추가적인 쿼리 메소드가 필요하면 여기에 작성
}

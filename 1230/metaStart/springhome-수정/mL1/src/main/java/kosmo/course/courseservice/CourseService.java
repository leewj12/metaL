package kosmo.course.courseservice;

import kosmo.course.coursedto.NationalCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    boolean NationalCourse(NationalCourseDTO nationalCourseDTO);

    List<NationalCourseDTO> getAllNationalCourses();

    Long getLastSessionNumberByCourseId(Long courseId);


    boolean NationalSessionCourse (NationalSessionCourseDTO NationalSessionCoursedto);

    List<NationalSessionCourseDTO> getSessionsByCourseId(Long courseId);

    Optional<NationalSessionCourseDTO> editSession(Long sessionId);


    boolean updateSession(NationalSessionCourseDTO editSessionDTO);


}

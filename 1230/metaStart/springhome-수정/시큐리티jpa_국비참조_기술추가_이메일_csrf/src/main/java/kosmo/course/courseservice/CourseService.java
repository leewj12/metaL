package kosmo.course.courseservice;

import kosmo.course.coursedto.NationalCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseSummaryDTO;

import java.util.List;

public interface CourseService {
    boolean NationalCourse(NationalCourseDTO nationalCourseDTO);

    List<NationalCourseDTO> getAllNationalCourses();

    Long getLastSessionNumberByCourseId(Long courseId);


    boolean NationalSessionCourse (NationalSessionCourseDTO NationalSessionCoursedto);



    List<NationalSessionCourseSummaryDTO>getSessionsByCourseId(Long courseId);

}

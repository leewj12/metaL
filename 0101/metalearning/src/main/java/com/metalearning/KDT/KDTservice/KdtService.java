package com.metalearning.KDT.KDTservice;

import com.metalearning.KDT.KDTdto.KDTCourseDto;
import com.metalearning.KDT.KDTdto.KDTSessionDto;

import java.util.List;
import java.util.Map;

public interface KdtService {

    int kdtcoursesave(KDTCourseDto kdtCourseDto);

    int kdtsessionsave(KDTSessionDto kdtSessionDto);


    List<KDTCourseDto> courseall();

    // courseId에 해당하는 세션 번호와 과정명을 반환
    Map<String, Object> getSessionNumAndCourseTitleByCourseId(Long courseId);


}

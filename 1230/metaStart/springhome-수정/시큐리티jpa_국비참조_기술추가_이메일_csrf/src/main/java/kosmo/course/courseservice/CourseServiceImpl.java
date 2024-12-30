package kosmo.course.courseservice;

import kosmo.course.coursedto.NationalCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseDTO;
import kosmo.course.coursedto.NationalSessionCourseSummaryDTO;
import kosmo.course.courseentity.NationalCourse;
import kosmo.course.courseentity.NationalSessionCourse;
import kosmo.course.courserepository.NationalCourseRepository;
import kosmo.course.courserepository.NationalSessionCourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final NationalCourseRepository nationalCourseRepository; // Repository 주입
    private final NationalSessionCourseRepository nationalSessionCourseRepository;

    @Override
    public boolean NationalCourse(NationalCourseDTO nationalCourseDTO) {
        try {
            // 먼저 동일한 과정명이 존재하는지 확인
            if (nationalCourseRepository.existsByCourseName(nationalCourseDTO.getCourseName())) {
                return false; // 이미 존재하는 경우 false 반환
            }

            // DTO에서 엔티티로 변환 (세터 없이 빌더 사용)
            NationalCourse nationalCourse = NationalCourse.builder()
                    .courseName(nationalCourseDTO.getCourseName())
                    .courseStatus(nationalCourseDTO.getCourseStatus())
                    .build();

            // 저장
            nationalCourseRepository.save(nationalCourse);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // 예외 처리
            return false;
        }
    }


    @Override
    public List<NationalCourseDTO> getAllNationalCourses() {
        // 엔티티 목록을 조회하고 DTO로 변환하여 반환
        List<NationalCourseDTO> courseall = nationalCourseRepository.findAll()
                .stream() // 엔티티 리스트를 스트림으로 변환
                .map(nationalCourse -> new NationalCourseDTO( // 각 엔티티를 DTO로 매핑
                        nationalCourse.getNationalCourseId(),  // 국비 과정 ID
                        nationalCourse.getCourseName(),        // 국비 과정명
                        nationalCourse.getCourseStatus()       // 수강 상태
                ))
                .collect(Collectors.toList()); // 결과를 리스트로 수집

        return courseall; // 리스트 반환
    }

    public Long getLastSessionNumberByCourseId(Long courseId) {
        try {
            // 해당 과정에 대한 마지막 회차를 조회
            Optional<NationalSessionCourse> lastSession =
                    nationalSessionCourseRepository.findTopByNationalCourse_nationalCourseIdOrderBySessionNumberDesc(courseId);

            // Optional로 반환되는 값을 안전하게 처리
            return lastSession.map(NationalSessionCourse::getSessionNumber).orElse(0L);
        } catch (Exception e) {
            // 예외 발생 시 로깅
            e.printStackTrace();
            return 0L;  // 기본적으로 0을 반환
        }
    }

    @Transactional
    @Override
    public boolean NationalSessionCourse(NationalSessionCourseDTO nationalSessionCoursedto) {
        log.info("입력 데이터: {}", nationalSessionCoursedto);

        // 1. 중복 체크: 같은 국비 과정과 회차가 이미 존재하는지 확인
        boolean exists = nationalSessionCourseRepository.existsByNationalCourseNationalCourseIdAndSessionName(
                nationalSessionCoursedto.getNationalCourseId(),
                nationalSessionCoursedto.getSessionName()
        );

        log.info("중복 체크 결과: {}", exists);

        if (exists) {
            log.info("중복된 과정입니다: 국비 과정 ID = {}, 회차명 = {}", nationalSessionCoursedto.getNationalCourseId(), nationalSessionCoursedto.getSessionName());
            return false;  // 이미 존재하는 경우, 저장하지 않고 false 반환
        }

        // 2. 새로운 국비 과정 회차 정보 저장
        NationalCourse nationalCourse = nationalCourseRepository.findById(nationalSessionCoursedto.getNationalCourseId())
                .orElseThrow(() -> new RuntimeException("국비 과정이 존재하지 않습니다.")); // 예외 처리
        log.info("찾은 국비 과정: {}", nationalCourse);

        NationalSessionCourse newSessionCourse = NationalSessionCourse.builder()
                .nationalCourse(nationalCourse)
                .sessionName(nationalSessionCoursedto.getSessionName())
                .sessionNumber(nationalSessionCoursedto.getSessionNumber())
                .sessionDescription(nationalSessionCoursedto.getSessionDescription())
                .sessionStartDate(nationalSessionCoursedto.getSessionStartDate())
                .sessionEndDate(nationalSessionCoursedto.getSessionEndDate())
                .curriculum(nationalSessionCoursedto.getCurriculum())
                .category(nationalSessionCoursedto.getCategory())
                .maxCapacity(nationalSessionCoursedto.getMaxCapacity())
                .isOnline(nationalSessionCoursedto.getIsOnline())
                .build();

        log.info("새로 생성된 국비 과정 회차: {}", newSessionCourse);

        // 데이터베이스에 저장
        nationalSessionCourseRepository.save(newSessionCourse);
        log.info("새로운 국비 과정 회차 저장됨: {}", newSessionCourse);

        return true; // 저장 성공 시 true 반환
    }

//    @Override
//    public List<NationalSessionCourseDTO> getSessionsByCourseId(Long courseId) {
//        // 해당 courseId에 맞는 회차 정보 조회
//        List<NationalSessionCourse> sessions = nationalSessionCourseRepository.findByNationalCourse_NationalCourseId(courseId);
//
//        // DTO로 변환하여 반환
//        return sessions.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//    // 엔티티를 DTO로 변환
//    private NationalSessionCourseDTO convertToDTO(NationalSessionCourse session) {
//        return NationalSessionCourseDTO.builder()
//                .nationalSessionCourseId(session.getNationalSessionCourseId())
//                .nationalCourseId(session.getNationalCourse().getNationalCourseId())
//                .sessionName(session.getSessionName())
//                .sessionNumber(session.getSessionNumber())
//                .sessionStartDate(session.getSessionStartDate())
//                .sessionEndDate(session.getSessionEndDate())
//                .sessionDescription(session.getSessionDescription())
//                .curriculum(session.getCurriculum())
//                .category(session.getCategory())
//                .maxCapacity(session.getMaxCapacity())
//                .isOnline(session.getIsOnline())
//                .build();
//    }


    @Override
    public List<NationalSessionCourseSummaryDTO> getSessionsByCourseId(Long courseId) {
        List<NationalSessionCourse> sessions = nationalSessionCourseRepository.findByNationalCourse_NationalCourseId(courseId);

        // DTO 변환을 DTO 클래스 내에서 처리
        return sessions.stream()
                .map(NationalSessionCourseSummaryDTO::fromEntity)  // 변환 메서드 호출
                .collect(Collectors.toList());
    }


//
//    @Override
//    public List<NationalSessionCourseSummaryDTO> getSessionsByCourseId(Long courseId) {
//        // 해당 courseId에 맞는 회차 정보 조회
//        List<NationalSessionCourse> sessions = nationalSessionCourseRepository.findByNationalCourse_NationalCourseId(courseId);
//
//        // 서비스에서 변환 로직 처리
//        return sessions.stream()
//                .map(this::convertToSummaryDTO)  // convertToSummaryDTO를 호출하여 변환
//                .collect(Collectors.toList());
//    }
//
//    // 엔티티를 SummaryDTO로 변환 (일부 데이터만)
//    private NationalSessionCourseSummaryDTO convertToSummaryDTO(NationalSessionCourse session) {
//        return NationalSessionCourseSummaryDTO.builder()
//                .nationalSessionCourseId(session.getNationalSessionCourseId())  // 회차 ID
//                .sessionName(session.getSessionName())  // 회차 이름
//                .sessionNumber(session.getSessionNumber())  // 회차 번호
//                .sessionStartDate(session.getSessionStartDate())  // 회차 시작 날짜
//                .sessionEndDate(session.getSessionEndDate())  // 회차 종료 날짜
//                .build();
//    }





}




package kosmo.test.testservice;

import kosmo.test.testdto.VideoDTO;
import kosmo.test.testentity.TestEntity;
import kosmo.test.testrepository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;

    @Override
    @Transactional
    public boolean videosave(VideoDTO videoDTO) {
        try {
            // VideoDTO에서 받은 데이터로 Entity 객체 생성
            TestEntity testEntity = TestEntity.builder()
                    .videoUuid(videoDTO.getUuid())  // UUID
                    .videoOriginalFileName(videoDTO.getOriginalFileName())  // 원본 파일 이름
                    .videoSize(videoDTO.getVideoSize())  // 파일 크기
                    .build();

            // DB에 저장
            testRepository.save(testEntity);

            return true; // 저장 성공 시 true 반환
        } catch (Exception e) {
            // 예외가 발생하면 로그를 출력하고 false 반환
            System.err.println("Error occurred while saving video: " + e.getMessage());
            return false;  // 예외 발생 시 false 반환
        }
    }

    @Override
    public VideoDTO findvideo() {
        try {
            // 첫 번째 동영상을 조회
            TestEntity testEntity = testRepository.findAll().get(0); // 첫 번째 동영상만 가져오기

            if (testEntity == null) {
                return null; // 동영상이 없으면 null 반환
            }

            // TestEntity를 VideoDTO로 변환
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setUuid(testEntity.getVideoUuid());
            videoDTO.setOriginalFileName(testEntity.getVideoOriginalFileName());
            videoDTO.setVideoSize(testEntity.getVideoSize());
            videoDTO.setMode("view"); // mode는 "view"로 설정

            return videoDTO;
        } catch (Exception e) {
            // 예외 발생 시 null 반환
            return null;
        }
    }
}

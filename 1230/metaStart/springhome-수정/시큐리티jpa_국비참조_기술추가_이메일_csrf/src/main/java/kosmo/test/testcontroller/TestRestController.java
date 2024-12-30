package kosmo.test.testcontroller;

import kosmo.test.testdto.VideoDTO;
import kosmo.test.testservice.TestService;
import kosmo.Utility.ResponseMagseg;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    private final TestService testService;

    @PostMapping("/api/video")
    public ResponseMagseg uploadVideo(@RequestParam("file") MultipartFile file) {
        try {
            // 업로드 경로 설정 (static 디렉토리를 사용)
            String uploadDir = "src/main/resources/static/video";  // 원래 경로 그대로 유지
            Path uploadPath = Paths.get(uploadDir);

            // 디렉토리가 없으면 생성
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // 파일명 처리 (UUID + 원본 파일명)
            String originalFileName = file.getOriginalFilename();
            String uuidFileName = UUID.randomUUID() + "_" + originalFileName;

            // 파일 저장
            Path filePath = uploadPath.resolve(uuidFileName);
            Files.write(filePath, file.getBytes());

            // VideoDTO 생성 및 값 설정
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setOriginalFileName(originalFileName);
            videoDTO.setUuid(uuidFileName);
            videoDTO.setVideoSize(String.valueOf(file.getSize())); // 파일 크기

            // mode 설정
            videoDTO.setMode("write");

            // Service를 통해 저장 처리
            boolean videosave = testService.videosave(videoDTO);

            // 저장 성공 시 응답 메시지 반환
            if (videosave) {
                return new ResponseMagseg("success", "File uploaded and saved successfully.");
            } else {
                return new ResponseMagseg("error", "Failed to save the video in the database.");
            }
        } catch (Exception e) {
            // 예외 발생 시 로그 출력
            System.err.println("Error occurred during file upload: " + e.getMessage());
            return new ResponseMagseg("error", "An error occurred during file upload: " + e.getMessage());
        }
    }

    // 동영상 조회 API (uuid로 동영상 찾기)
    @GetMapping("/api/videoview")
    public ResponseMagseg viewVideo() {
        try {
            // 동영상 하나만 찾기
            VideoDTO videoDTO = testService.findvideo();

            // 동영상 정보가 없으면 에러 메시지 반환
            if (videoDTO == null) {
                return new ResponseMagseg("error", "Video not found.");
            }

            // 동영상 경로 반환
            String videoUrl = "/static/video/" + videoDTO.getUuid(); // 정적 리소스로 접근
            return new ResponseMagseg("success", videoUrl);  // 경로를 반환
        } catch (Exception e) {
            return new ResponseMagseg("error", "An error occurred while fetching the video: " + e.getMessage());
        }
    }
}


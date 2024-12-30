package kosmo.test.testdto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class VideoDTO {

    private String uuid; // 고유 식별자
    private String originalFileName; // 원본 파일 이름
    private String videoSize; // 파일 크기
    private String mode; // 모드 (예: "write" 또는 다른 상태)

}

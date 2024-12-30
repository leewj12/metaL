package kosmo.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SignUpDTO {

    private String name;
    private String useremail;
    private String password;
    private String usergender;
    private LocalDate userbirth;
    private String userPhoneNumber;  // 전화번호 필드 이름 변경
    private boolean userpersonalInfoConsent;
    private boolean usermarketingConsent;
}

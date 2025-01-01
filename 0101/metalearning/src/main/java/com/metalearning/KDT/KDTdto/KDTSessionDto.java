package com.metalearning.KDT.KDTdto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@ToString
public class KDTSessionDto {

    private Long KDTSessionId;
    private Long KDTCourseId;  // 수정된 필드명 (Long 타입)
    private int KDTSessionNum;
    private String KDTSessionTitle;
    private String KDTSessionDescript;
    private LocalDate KDTSessionStartDate;
    private LocalDate KDTSessionEndDate;
    private String KDTSessionCategory;
    private int KDTSessionMaxCapacity;
    private String KDTSessionThumbnail;
    private LocalDateTime KDTSessionStartTime;
    private LocalDateTime KDTSessionEndTime;
    private String KDTSessionPostcode;
    private String KDTSessionAddress;
    private String KDTSessionAddressDetail;
    private Boolean KDTSessionOnline;


}

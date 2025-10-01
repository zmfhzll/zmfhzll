package com.sol.zmfhzll.domain.user.dto;

import com.sol.zmfhzll.domain.user.UserEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Builder
public class UserTestResponse {
    private Long userNo;
    private String userId;
    private String userEmail;
    private String userName;
    private String userPassword;
    private String userCreatedAt;
    private String userTimeZone;

    // 사용자 저장된 타임존으로 변환
    public static UserTestResponse from(UserEntity user) {
        ZoneId userZone = ZoneId.of(user.getUserTimezone());
        ZonedDateTime zonedTime = user.getUserCreatedAt().atZone(userZone);

        return UserTestResponse.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .userPassword(user.getUserPassword())
                .userCreatedAt(zonedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .userTimeZone(user.getUserTimezone())
                .build();
    }

    // 클라이언트가 지정한 타임존으로 변환
    public static UserTestResponse fromWithTimezone(UserEntity user, String timezone) {
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime zonedTime = user.getUserCreatedAt().atZone(zoneId);

        return UserTestResponse.builder()
                .userNo(user.getUserNo())
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .userName(user.getUserName())
                .userCreatedAt(zonedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .userTimeZone(timezone)
                .build();
    }
}

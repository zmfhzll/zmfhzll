package com.sol.zmfhzll.user;

import com.sol.zmfhzll.domain.user.UserEntity;
import com.sol.zmfhzll.domain.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testTimezoneConversion() {
        // Given
//        UserEntity user = UserEntity.builder()
//                .userId("admin")
//                .userEmail("admin@test.com")
//                .userName("관리자")
//                .userPassword("1")
//                .userTimezone("Asia/Seoul")
//                .build();

        UserEntity user2 = UserEntity.builder()
                .userId("manager")
                .userEmail("manager@test.com")
                .userName("매니저")
                .userPassword("1")
                .userTimezone("Asia/Seoul")
                .build();

        // When
        UserEntity savedUser =  userRepository.save(user2);

        // Then
        assertNotNull(savedUser.getUserCreatedAt());

        // UTC로 저장되었지만, 한국 시간으로 변환
        ZonedDateTime koreanTime = savedUser.getUserCreatedAt()
                .atZone(ZoneId.of("Asia/Seoul"));

        System.out.println("UTC 시간: " + savedUser.getUserCreatedAt());
        System.out.println("한국 시간: " + koreanTime);

        // 미국 시간으로도 변환 가능
        ZonedDateTime usTime = savedUser.getUserCreatedAt()
                .atZone(ZoneId.of("America/New_York"));

        System.out.println("미국 동부 시간: " + usTime);

    }
}

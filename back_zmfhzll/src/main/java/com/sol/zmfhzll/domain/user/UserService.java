package com.sol.zmfhzll.domain.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserEntity register(String userId, String userEmail, String userName, String userPassword, String timezone) {
        validateTimezone(timezone);

        UserEntity userEntity = UserEntity.builder()
                .userId(userId)
                .userName(userName)
                .userPassword(userPassword)
                .userEmail(userEmail)
                .userTimezone(timezone != null ? timezone : "UTC")
                .build();

        return userRepository.save(userEntity);
    }

    public UserEntity findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private void validateTimezone(String timezone){
        if (timezone == null) {
            return;
        }
        try {
            ZoneId.of(timezone);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid timezone" + timezone);
        }
    }
}

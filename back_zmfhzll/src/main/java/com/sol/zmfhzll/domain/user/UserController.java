package com.sol.zmfhzll.domain.user;

import com.sol.zmfhzll.domain.user.dto.UserRegisterTestRequest;
import com.sol.zmfhzll.domain.user.dto.UserTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 사용자 저장된 타임존으로 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserTestResponse> getUser(@PathVariable Long id) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok(UserTestResponse.from(user));
    }

    // 클라이언트가 보낸 타임존으로 조회
    @GetMapping("/{id}/timezone")
    public ResponseEntity<UserTestResponse> getUserWithTimezone(
            @PathVariable Long id,
            @RequestHeader(value = "X-Timezone", defaultValue = "UTC") String timezone
    ) {
        UserEntity user = userService.findById(id);
        return ResponseEntity.ok(UserTestResponse.fromWithTimezone(user, timezone));
    }

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserTestResponse> register(@RequestBody UserRegisterTestRequest request) {
        UserEntity user = userService.register(
                request.getUserId(),
                request.getUserEmail(),
                request.getUserName(),
                request.getUserPassword(),
                request.getTimezone()
        );
        return ResponseEntity.ok(UserTestResponse.from(user));
    }
}
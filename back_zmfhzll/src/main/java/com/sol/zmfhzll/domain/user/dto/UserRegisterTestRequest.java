package com.sol.zmfhzll.domain.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterTestRequest {
    private String userId;
    private String userEmail;
    private String userPassword;
    private String userName;
    private String timezone;
}

package kz.cleangov.cleangov.resource;

import lombok.Data;

@Data
public class UserLoginRequest {
    private String username;
    private String password;
}

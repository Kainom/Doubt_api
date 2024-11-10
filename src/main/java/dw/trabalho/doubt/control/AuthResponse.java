package dw.trabalho.doubt.control;

import dw.trabalho.doubt.control.dto.UserDto;
import dw.trabalho.doubt.model.User;

public class AuthResponse {
    private String token;
    private String role;
    private UserDto userDto;





     public AuthResponse() {

    }

    public AuthResponse(String token, String role,UserDto userDto) {
        this.token = token;
        this.role = role;
        this.userDto = userDto;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
    public UserDto getUser() {
        return userDto;
    }

}

package ua.unblv.payload.response;

import lombok.Getter;

@Getter
public class InvalidLoginResponse {
    private final String username;
    private final String password;

    public InvalidLoginResponse() {
        this.username = "Invalid username";
        this.password = "Invalid password";
    }
}


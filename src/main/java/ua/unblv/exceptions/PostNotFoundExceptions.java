package ua.unblv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PostNotFoundExceptions extends RuntimeException {
    public PostNotFoundExceptions(String postNotFound) {
        super(postNotFound);
    }
}

package com.leozeballos.blog.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BlogAppException extends RuntimeException {

    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public BlogAppException(HttpStatus status, String message, String message1) {
        super(message);
        this.status = status;
        // this.message = message;
        this.message = message1;
    }

}

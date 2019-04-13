package pl.dabrowski.demoapp.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExceptionProductNotFound extends RuntimeException {

    public ExceptionProductNotFound(String exception){
        super(exception);
    }
}

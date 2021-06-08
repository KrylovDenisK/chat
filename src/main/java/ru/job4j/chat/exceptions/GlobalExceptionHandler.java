package ru.job4j.chat.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> noSuchException(NoSuchModelException e) {
        return new ResponseEntity<>(
                new IncorrectData(e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }
}

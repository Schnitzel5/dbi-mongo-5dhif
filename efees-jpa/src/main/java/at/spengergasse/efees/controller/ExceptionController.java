package at.spengergasse.efees.controller;

import at.spengergasse.efees.exception.NotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = {"at.spengergasse.efees.controller"})
public class ExceptionController {
    @ExceptionHandler(value = NotValidException.class)
    public ResponseEntity<Object> handleNotValid(NotValidException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

package manh.com.project.SaleManagement.exceptionHandler;

import manh.com.project.SaleManagement.exceptions.PhoneNotFoundException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class HandlerEmptyResult {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handlerEmptyResultDataAccessException(EmptyResultDataAccessException e) {
            return "Have some error. Try Again!";
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PhoneNotFoundException.class)
    public String handlerPhoneNotFoundException(PhoneNotFoundException e) {
        return "Your account has not been identified. Please fill out all information and come back";
    }
}

package com.lucasgoiana.helpdesk.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;


@ControllerAdvice
public class ResourceExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validationErrors(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {

        ValidationError errors = new ValidationError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "Erro na validação dos campos", request.getRequestURI());

        for(FieldError x : ex.getBindingResult().getFieldErrors()) {
            errors.addError(x.getField(), x.getDefaultMessage());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<StandardError> validationErrors(TransactionSystemException ex,
                                                          HttpServletRequest request) {

        StandardError errors = new StandardError(LocalDate.now(), HttpStatus.BAD_REQUEST.value(),
                "Validation error", "Número do registro de contribuinte individual brasileiro (CPF) inválido", request.getRequestURI());


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }
}

package com.lcwd.todo.exceptions;

import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    // we have to create handler method for specific execption
    Logger logger= (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
@ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handlenullpointerexception(NullPointerException ex){
logger.info("Its nullpointer exception from global handler");
return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
@ExceptionHandler(ResourceNotFoundExcetion.class)
    public  ResponseEntity<ExceptionResponse> handleResorceNotFoundException(ResourceNotFoundExcetion ex){
logger.error("ERROR: {}",ex.getMessage());
ExceptionResponse response =new ExceptionResponse();
response.setMessage(ex.getMessage());
response.setStatus(HttpStatus.NOT_FOUND);
response.setSuccess(false);
return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);


    }

}

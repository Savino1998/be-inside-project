package com.reply.insideproject.advice;

import com.reply.insideproject.error.ApiError;
import com.reply.insideproject.exception.AlreadyExistsException;
import com.reply.insideproject.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiError(HttpStatus.NOT_FOUND.toString(), e.getMessage()));
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiError> alreadyExistsException(AlreadyExistsException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiError> dataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.badRequest().body(new ApiError(HttpStatus.BAD_REQUEST.toString(), e.getMostSpecificCause().getMessage()));
    }

}

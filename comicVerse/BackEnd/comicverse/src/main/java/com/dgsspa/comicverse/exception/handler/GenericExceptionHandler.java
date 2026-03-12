package com.dgsspa.comicverse.exception.handler;

import com.dgsspa.comicverse.exception.ErrorResult;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResult> handleNotFoundException(ResourceNotFoundException ex) {
        log.warn("Risorsa non trovata: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResult("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleGenericException(Exception ex) {
        log.error("Errore non gestito", ex.getLocalizedMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResult("UNEXPECTED_ERROR", "Errore imprevisto"));
    }
}

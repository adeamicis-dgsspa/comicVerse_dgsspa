package com.dgsspa.comicverse.exception.handler;

import com.dgsspa.comicverse.exception.ErrorResult;
import com.dgsspa.comicverse.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GenericExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GenericExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResult> handleNotFoundException(ResourceNotFoundException ex) {
        log.info("Risorsa non trovata: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResult("NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResult> handleGenericException(Exception ex, HttpServletRequest request) {
        String qs = request.getQueryString();
        String fullPath = qs == null ? request.getRequestURI() : request.getRequestURI() + "?" + qs;
        Throwable root = rootCause(ex);
        if (root != ex) {
            log.error(
                    "Errore non gestito su -{} {}\n- {}: {}\n-root: {}: {}",
                    request.getMethod(),
                    fullPath,
                    ex.getClass().getName(),
                    ex.getMessage(),
                    root.getClass().getName(),
                    root.getMessage()
            );
        } else {
            log.error(
                    "Errore non gestito su {} {} - {}: {}",
                    request.getMethod(),
                    fullPath,
                    ex.getClass().getName(),
                    ex.getMessage()
            );
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResult("UNEXPECTED_ERROR", "Errore imprevisto"));
    }

    private static Throwable rootCause(Throwable ex) {
        Throwable current = ex;
        while (current.getCause() != null && current.getCause() != current) {
            current = current.getCause();
        }
        return current;
    }
}

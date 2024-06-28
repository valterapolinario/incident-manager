package com.br.manager.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.SECONDS;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class CustomHandler extends ResponseEntityExceptionHandler {

    @Override
    @ResponseStatus(BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final List<FieldError> errors = ex.getFieldErrors();
        ProblemDetail detail = ProblemDetail.forStatus(BAD_REQUEST);

        Map<String, Set<Map<String, String>>> errorMap = errors.stream()
                .collect(Collectors.groupingBy(
                        FieldError::getField,
                        Collectors.mapping(fieldError -> Map.of(
                                "message", Objects.requireNonNull(fieldError.getDefaultMessage()),
                                "rejectedValue", fieldError.getRejectedValue() != null ? fieldError.getRejectedValue().toString() : "null"
                        ), Collectors.toSet())
                ));

        detail.setTitle("Validation failed");
        detail.setStatus(BAD_REQUEST.value());
        detail.setProperty("time", LocalDateTime.now
                        (ZoneId
                                .of("America/Sao_Paulo"))
                .truncatedTo(SECONDS));
        detail.setProperty("errors", errorMap);
        return ResponseEntity.status(BAD_REQUEST).body(detail);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrity(DataIntegrityViolationException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(PRECONDITION_FAILED);
        detail.setTitle("Precondition failed");
        detail.setStatus(PRECONDITION_FAILED.value());
        detail.setProperty("time", LocalDateTime.now
                        (ZoneId
                                .of("America/Sao_Paulo"))
                .truncatedTo(SECONDS));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(PRECONDITION_FAILED).body(detail);
    }

    @ExceptionHandler(ApiBussinesException.class)
    public ResponseEntity<Object> handleDataIntegrity(ApiBussinesException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(BAD_REQUEST);
        detail.setTitle("bad request");
        detail.setStatus(BAD_REQUEST.value());
        detail.setProperty("time", LocalDateTime.now
                        (ZoneId
                                .of("America/Sao_Paulo"))
                .truncatedTo(SECONDS));
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(detail);
    }

    @ExceptionHandler(GeneralNotFoundException.class)
    public ResponseEntity<Object> handleNotFound(GeneralNotFoundException ex) {
        ProblemDetail detail = ProblemDetail.forStatus(NOT_FOUND);
        detail.setTitle("Object not found");
        detail.setStatus(NOT_FOUND.value());
        detail.setProperty("time", LocalDateTime.now
                        (ZoneId
                                .of("America/Sao_Paulo"))
                .truncatedTo(SECONDS));
        detail.setProperty("value not found : ", ex.getValueNotFound());
        detail.setDetail(ex.getMessage());
        return ResponseEntity.status(NOT_FOUND).body(detail);
    }
}

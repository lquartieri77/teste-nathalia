package br.com.teste.anagrama.teste_leonardo.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        var fieldError = ex.getBindingResult().getFieldError();

        Map<String, Object> problemDetails = new HashMap<>();
        problemDetails.put("type", "https://httpstatuses.com/400");
        problemDetails.put("title", "Erro de validação");
        problemDetails.put("status", HttpStatus.BAD_REQUEST.value());
        problemDetails.put("detail", fieldError != null ? fieldError.getDefaultMessage() : "Erro de validação.");
        problemDetails.put("instance", "/api/anagramas");

        return ResponseEntity.badRequest().body(problemDetails);
    }
}
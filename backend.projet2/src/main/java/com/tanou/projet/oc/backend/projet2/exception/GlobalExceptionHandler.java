package com.tanou.projet.oc.backend.projet2.exception;

import com.tanou.projet.oc.backend.projet2.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
    ErrorResponseDto errorResponse = new ErrorResponseDto(errorMessage);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PictureStorageException.class)
  public ResponseEntity<ErrorResponseDto> handlePictureStorageException(PictureStorageException ex) {
    ErrorResponseDto errorResponse = new ErrorResponseDto(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
    ErrorResponseDto errorResponse = new ErrorResponseDto("Tous les champs sont obligatoires");
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }
}

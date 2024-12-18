package com.tanou.projet.oc.backend.projet2.dto;

public class ErrorResponseDto {
  private String message;

  public ErrorResponseDto(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

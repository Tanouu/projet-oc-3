package com.tanou.projet.oc.backend.projet2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateMessageDto {

  @NotBlank(message = "Le message ne doit pas être vide")
  private String message;

  @NotNull(message = "L'id de l'utilisateur est obligatoire")
  private Integer user_id;

  @NotNull(message = "L'id de la location est obligatoire")
  private Integer rental_id;

  public CreateMessageDto() {
  }

  public CreateMessageDto(String message, Integer user_id, Integer rental_id) {
    this.message = message;
    this.user_id = user_id;
    this.rental_id = rental_id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public Integer getRental_id() {
    return rental_id;
  }

  public void setRental_id(Integer rental_id) {
    this.rental_id = rental_id;
  }
}

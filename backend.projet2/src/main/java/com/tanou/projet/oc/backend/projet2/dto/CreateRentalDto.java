package com.tanou.projet.oc.backend.projet2.dto;

import java.math.BigDecimal;

public class CreateRentalDto {
  private String name;
  private BigDecimal surface;
  private BigDecimal price;
  private String picture;
  private String description;
  private Integer owner_id;

  public CreateRentalDto(String name, BigDecimal surface, BigDecimal price, String picture, String description, Integer owner_id) {
    this.name = name;
    this.surface = surface;
    this.price = price;
    this.picture = picture;
    this.description = description;
    this.owner_id = owner_id;
  }

  public CreateRentalDto() {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getSurface() {
    return surface;
  }

  public void setSurface(BigDecimal surface) {
    this.surface = surface;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getOwner_id() {
    return owner_id;
  }

  public void setOwner_id(Integer owner_id) {
    this.owner_id = owner_id;
  }
}

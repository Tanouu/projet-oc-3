package com.tanou.projet.oc.backend.projet2.dto;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

public class CreateRentalForm {

  @Schema(description = "Nom de la location", example = "Appartement T2")
  @NotBlank(message = "Le nom ne peut pas être vide")
  private String name;

  @Schema(description = "Surface de la location en m²", example = "45.5")
  @NotNull(message = "La surface est obligatoire")
  private BigDecimal surface;

  @Schema(description = "Prix de la location", example = "1000")
  @NotNull(message = "Le prix est obligatoire")
  private BigDecimal price;

  @Schema(description = "Description de la location", example = "Un bel appartement T2 au centre-ville")
  @NotBlank(message = "La description ne peut pas être vide")
  private String description;

  @Schema(description = "Image associée à la location", type = "string", format = "binary")
  private MultipartFile picture;

  @Schema(description = "ID du propriétaire", example = "1", required = false)
  private Integer owner_id;

  // Getters et setters...
  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public BigDecimal getSurface() { return surface; }
  public void setSurface(BigDecimal surface) { this.surface = surface; }

  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }

  public String getDescription() { return description; }
  public void setDescription(String description) { this.description = description; }

  public MultipartFile getPicture() { return picture; }
  public void setPicture(MultipartFile picture) { this.picture = picture; }

  public Integer getOwner_id() { return owner_id; }
  public void setOwner_id(Integer owner_id) { this.owner_id = owner_id; }

  public CreateRentalDto toCreateRentalDto() {

    return new CreateRentalDto(name, surface, price, description, owner_id);
  }
}

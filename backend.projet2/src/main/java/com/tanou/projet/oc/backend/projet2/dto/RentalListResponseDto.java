package com.tanou.projet.oc.backend.projet2.dto;

import java.util.List;

public class RentalListResponseDto {
  private List<RentalDto> rentals;

  public RentalListResponseDto(List<RentalDto> rentals) {
    this.rentals = rentals;
  }

  public List<RentalDto> getRentals() {
    return rentals;
  }

  public void setRentals(List<RentalDto> rentals) {
    this.rentals = rentals;
  }
}

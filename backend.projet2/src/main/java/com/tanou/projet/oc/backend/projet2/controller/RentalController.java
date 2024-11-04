package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  private final RentalService rentalService;


  @Autowired
  public RentalController(RentalService rentalService) {
    this.rentalService = rentalService;
  }

  @GetMapping
  public ResponseEntity<List<RentalDto>> getAllRentals() {
    return ResponseEntity.ok(rentalService.getAllRentals());
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalById(@PathVariable Integer id) {
    return ResponseEntity.ok(rentalService.getRentalById(id));
  }


  @PutMapping("/{id}")
  public ResponseEntity<RentalDto> updateRental(@PathVariable Integer id, @RequestBody CreateRentalDto createRentalDto) {
    return ResponseEntity.ok(rentalService.updateRental(id, createRentalDto));
  }

}

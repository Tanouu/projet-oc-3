package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

  private final RentalService rentalService;
  private final UserService userService;

  @Autowired
  public RentalController(RentalService rentalService, UserService userService) {
    this.rentalService = rentalService;
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Map<String, List<RentalDto>>> getAllRentals() {
    List<RentalDto> rentals = rentalService.getAllRentals();
    Map<String, List<RentalDto>> response = Map.of("rentals", rentals);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalById(@PathVariable Integer id) {
    return ResponseEntity.ok(rentalService.getRentalById(id));
  }

  @PostMapping
  public ResponseEntity<RentalDto> createRental(
    @Valid @ModelAttribute CreateRentalDto createRentalDto,
    @RequestParam("picture") MultipartFile picture,
    Principal principal) throws IOException {

    String email = principal.getName();
    User currentUser = userService.findUserByEmail(email);
    createRentalDto.setOwner_id(currentUser.getId());

    return ResponseEntity.ok(rentalService.createRental(createRentalDto, picture));
  }

  @PutMapping("/{id}")
  public ResponseEntity<RentalDto> updateRental(
    @PathVariable Integer id,
    @Valid @ModelAttribute CreateRentalDto createRentalDto,
    @RequestParam("picture") MultipartFile picture,
    Principal principal) throws IOException {

    String email = principal.getName();
    User currentUser = userService.findUserByEmail(email);
    createRentalDto.setOwner_id(currentUser.getId());

    return ResponseEntity.ok(rentalService.updateRental(id, createRentalDto));
  }
}

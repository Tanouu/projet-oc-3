package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;

import java.security.Principal;
import java.util.List;

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

  @PostMapping
  public ResponseEntity<RentalDto> createRental(
    @RequestParam("name") String name,
    @RequestParam("description") String description,
    @RequestParam("price") BigDecimal price,
    @RequestParam("surface") BigDecimal surface,
    @RequestParam("picture") MultipartFile picture,
    Principal principal) throws IOException {

    // Récupérer l'utilisateur connecté par email (username)
    String email = principal.getName();
    User currentUser = userService.findUserByEmail(email); // Assurez-vous d'avoir une méthode qui trouve l'utilisateur par email

    // Créer un nouvel objet CreateRentalDto
    CreateRentalDto createRentalDto = new CreateRentalDto();
    createRentalDto.setName(name);
    createRentalDto.setDescription(description);
    createRentalDto.setPrice(price);
    createRentalDto.setSurface(surface);
    createRentalDto.setOwner_id(currentUser.getId()); // Utilisez l'ID de l'utilisateur authentifié

    return ResponseEntity.ok(rentalService.createRental(createRentalDto, picture));
  }
}

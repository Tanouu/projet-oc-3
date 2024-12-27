package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.*;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import com.tanou.projet.oc.backend.projet2.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
  public ResponseEntity<RentalListResponseDto> getAllRentals() {
    List<RentalDto> rentals = rentalService.getAllRentals();
    return ResponseEntity.ok(new RentalListResponseDto(rentals));
  }


  @GetMapping("/{id}")
  public ResponseEntity<RentalDto> getRentalById(@PathVariable Integer id) {
    return ResponseEntity.ok(rentalService.getRentalById(id));
  }

  @PostMapping(consumes = "multipart/form-data")
  @Operation(
    summary = "Créer une nouvelle location",
    description = "Créer une nouvelle location avec des données et une image",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "multipart/form-data",
        schema = @Schema(implementation = CreateRentalForm.class)
      )
    )
  )
  public ResponseEntity<RentalDto> createRental(
    @Valid @ModelAttribute CreateRentalForm createRentalForm,
    Principal principal
  ) throws IOException {
    String email = principal.getName();
    User currentUser = userService.findUserByEmail(email);
    createRentalForm.setOwner_id(currentUser.getId());
    try {
      return ResponseEntity.ok(rentalService.createRental(createRentalForm.toCreateRentalDto(), createRentalForm.getPicture()));
    }
    catch (Exception e) {
      return ResponseEntity.badRequest().build();
    }
  }

  @PutMapping("/{id}")
  @Operation(
    summary = "Mettre à jour une location",
    description = "Mettre à jour uniquement les informations d'une location, sans changer la photo",
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
      content = @Content(
        mediaType = "multipart/form-data",
        schema = @Schema(implementation = UpdateRentalDto.class)
      )
    )
  )
  public ResponseEntity<RentalDto> updateRental(
    @PathVariable Integer id,
    @Valid @ModelAttribute UpdateRentalDto updateRentalDto,
    Principal principal
  ) {
    String email = principal.getName();
    User currentUser = userService.findUserByEmail(email);
    updateRentalDto.setOwner_id(currentUser.getId());

    return ResponseEntity.ok(rentalService.updateRental(id, updateRentalDto));
  }

}

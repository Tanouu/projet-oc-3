package com.tanou.projet.oc.backend.projet2.service;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.entity.Rental;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RentalService {
  List<RentalDto> getAllRentals();
  RentalDto getRentalById(Integer id);
  RentalDto createRental(CreateRentalDto createRentalDto, MultipartFile picture);
  RentalDto updateRental(Integer id, CreateRentalDto createRentalDto);

}

package com.tanou.projet.oc.backend.projet2.service.impl;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.entity.Rental;
import com.tanou.projet.oc.backend.projet2.repository.RentalRepository;
import com.tanou.projet.oc.backend.projet2.repository.UserRepository;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

  private final RentalRepository rentalRepository;

  @Autowired
  public RentalServiceImpl(RentalRepository rentalRepository) {
    this.rentalRepository = rentalRepository;
  }

  @Override
  public List<RentalDto> getAllRentals() {
    return rentalRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
  }

  @Override
  public RentalDto getRentalById(Integer id) {
    Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    return convertToDto(rental);
  }

  @Override
  public RentalDto updateRental(Integer id, CreateRentalDto createRentalDto) {
    Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    rental.setName(createRentalDto.getName());
    rental.setSurface(createRentalDto.getSurface());
    rental.setPrice(createRentalDto.getPrice());
    rental.setPicture(createRentalDto.getPicture());
    rental.setDescription(createRentalDto.getDescription());
    rental.setUpdatedAt(createRentalDto.getUpdatedAt());
    rental = rentalRepository.save(rental);
    return convertToDto(rental);
  }


  private RentalDto convertToDto(Rental rental) {
    RentalDto rentalDto = new RentalDto();
    rentalDto.setId(rental.getId());
    rentalDto.setName(rental.getName());
    rentalDto.setSurface(rental.getSurface());
    rentalDto.setPrice(rental.getPrice());
    rentalDto.setPicture(rental.getPicture());
    rentalDto.setDescription(rental.getDescription());
    rentalDto.setOwnerId(rental.getOwner().getId());
    rentalDto.setCreatedAt(rental.getCreatedAt());
    rentalDto.setUpdatedAt(rental.getUpdatedAt());
    return rentalDto;
  }

}

package com.tanou.projet.oc.backend.projet2.service.impl;

import com.tanou.projet.oc.backend.projet2.dto.CreateRentalDto;
import com.tanou.projet.oc.backend.projet2.dto.RentalDto;
import com.tanou.projet.oc.backend.projet2.dto.UpdateRentalDto;
import com.tanou.projet.oc.backend.projet2.entity.Rental;
import com.tanou.projet.oc.backend.projet2.exception.PictureStorageException;
import com.tanou.projet.oc.backend.projet2.repository.RentalRepository;
import com.tanou.projet.oc.backend.projet2.repository.UserRepository;
import com.tanou.projet.oc.backend.projet2.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalServiceImpl implements RentalService {

  private final RentalRepository rentalRepository;
  private final UserRepository userRepository;

  @Autowired
  public RentalServiceImpl(RentalRepository rentalRepository, UserRepository userRepository) {
    this.rentalRepository = rentalRepository;
    this.userRepository = userRepository;
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
  public RentalDto createRental(CreateRentalDto createRentalDto, MultipartFile picture) throws IOException {

    Rental rental = new Rental();
    rental.setName(createRentalDto.getName());
    rental.setSurface(createRentalDto.getSurface());
    rental.setPrice(createRentalDto.getPrice());
    rental.setDescription(createRentalDto.getDescription());
    rental.setOwner(userRepository.findById(createRentalDto.getOwner_id()).orElseThrow(() -> new RuntimeException("Owner not found")));
    rental.setCreatedAt(LocalDateTime.now());
    rental.setUpdatedAt(LocalDateTime.now());

    if (picture == null) {
      throw new RuntimeException("Picture is required");
    }

    String picturePath = savePicture(picture);
    rental.setPicture(picturePath);

    rental = rentalRepository.save(rental);
    return convertToDto(rental);
  }


  @Override
  public RentalDto updateRental(Integer id, UpdateRentalDto updateRentalDto) {
    Rental rental = rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
    rental.setName(updateRentalDto.getName());
    rental.setSurface(updateRentalDto.getSurface());
    rental.setPrice(updateRentalDto.getPrice());
    rental.setDescription(updateRentalDto.getDescription());
    rental.setOwner(userRepository.findById(updateRentalDto.getOwner_id()).orElseThrow(() -> new RuntimeException("Owner not found")));
    rental.setUpdatedAt(LocalDateTime.now());

    rental = rentalRepository.save(rental);
    return convertToDto(rental);
  }

  @Value("${server.base-url}")
  private String serverUrl;

  private String savePicture(MultipartFile picture) {
    String folder = "uploads/";
    try {
      byte[] bytes = picture.getBytes();
      Path path = Paths.get(folder + picture.getOriginalFilename());
      Files.write(path, bytes);

      return serverUrl + "/uploads/" + picture.getOriginalFilename();
    } catch (IOException e) {
      throw new PictureStorageException("Failed to store picture", e);
    }
  }

  private RentalDto convertToDto(Rental rental) {
    RentalDto rentalDto = new RentalDto();
    rentalDto.setId(rental.getId());
    rentalDto.setName(rental.getName());
    rentalDto.setSurface(rental.getSurface());
    rentalDto.setPrice(rental.getPrice());
    rentalDto.setPicture(rental.getPicture());
    rentalDto.setDescription(rental.getDescription());
    rentalDto.setOwner_id(rental.getOwner().getId());
    rentalDto.setCreated_at(rental.getCreatedAt());
    rentalDto.setUpdated_at(rental.getUpdatedAt());
    return rentalDto;
  }
}

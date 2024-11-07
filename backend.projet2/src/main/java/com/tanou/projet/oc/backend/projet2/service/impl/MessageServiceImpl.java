package com.tanou.projet.oc.backend.projet2.service.impl;

import com.tanou.projet.oc.backend.projet2.dto.CreateMessageDto;
import com.tanou.projet.oc.backend.projet2.entity.Message;
import com.tanou.projet.oc.backend.projet2.entity.User;
import com.tanou.projet.oc.backend.projet2.entity.Rental;
import com.tanou.projet.oc.backend.projet2.repository.MessageRepository;
import com.tanou.projet.oc.backend.projet2.repository.UserRepository;
import com.tanou.projet.oc.backend.projet2.repository.RentalRepository;
import com.tanou.projet.oc.backend.projet2.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Service
public class MessageServiceImpl implements MessageService {

  private final MessageRepository messageRepository;
  private final UserRepository userRepository;
  private final RentalRepository rentalRepository;

  @Autowired
  public MessageServiceImpl(MessageRepository messageRepository, UserRepository userRepository, RentalRepository rentalRepository) {
    this.messageRepository = messageRepository;
    this.userRepository = userRepository;
    this.rentalRepository = rentalRepository;
  }

  @Override
  public void createMessage(CreateMessageDto createMessageDto) {
    User user = userRepository.findById(createMessageDto.getUser_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    Rental rental = rentalRepository.findById(createMessageDto.getRental_id()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rental not found"));

    Message message = new Message();
    message.setMessage(createMessageDto.getMessage());
    message.setUser(user);
    message.setRental(rental);
    message.setCreatedAt(LocalDateTime.now());
    message.setUpdatedAt(LocalDateTime.now());

    messageRepository.save(message);
  }
}

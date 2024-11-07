package com.tanou.projet.oc.backend.projet2.controller;

import com.tanou.projet.oc.backend.projet2.dto.CreateMessageDto;
import com.tanou.projet.oc.backend.projet2.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

  private final MessageService messageService;

  @Autowired
  public MessageController(MessageService messageService) {
    this.messageService = messageService;
  }

  @PostMapping
  public ResponseEntity<Void> createMessage(@RequestBody CreateMessageDto createMessageDto) {
    messageService.createMessage(createMessageDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}

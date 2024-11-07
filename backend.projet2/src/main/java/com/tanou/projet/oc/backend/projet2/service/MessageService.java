package com.tanou.projet.oc.backend.projet2.service;

import com.tanou.projet.oc.backend.projet2.dto.CreateMessageDto;


public interface MessageService {
  void createMessage(CreateMessageDto messageDto);
}

package com.tanou.projet.oc.backend.projet2.service;

import com.tanou.projet.oc.backend.projet2.dto.CreateMessageDto;

import java.util.List;

public interface MessageService {
  void createMessage(CreateMessageDto messageDto);
}

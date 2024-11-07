package com.tanou.projet.oc.backend.projet2.repository;

import com.tanou.projet.oc.backend.projet2.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Integer> {

}

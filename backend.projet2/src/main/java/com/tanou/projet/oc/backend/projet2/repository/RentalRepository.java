package com.tanou.projet.oc.backend.projet2.repository;

import com.tanou.projet.oc.backend.projet2.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
}

package com.projetojpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetojpa.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {

}

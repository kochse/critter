package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.repositories.PetRepository;

public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }
}

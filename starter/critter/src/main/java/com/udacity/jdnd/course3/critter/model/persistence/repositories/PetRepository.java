package com.udacity.jdnd.course3.critter.model.persistence.repositories;

import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet, Long> {
    public Optional<Pet> findById(Long id);
    public List<Pet> findByName(String name);

    public List<Pet> findByOwner(User owner);
}
package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.PetRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {
    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    /**
     * Either creates or updates a pet, based on prior existence of pet
     * @param pet A pet object, which can be either new or existing
     * @return the new/updated pet is stored in the repository
     */
    public Pet savePet(Pet pet) {
//        try {
//            //Optional<Pet> optional = repository.findById(pet.getId());
//
//            if (optional.isPresent()) {
//                Pet existingPet = optional.get();
//                //existingPet.setName(pet.getName());
//
//                return repository.save(existingPet);
//            }
//        } catch (EntityNotFoundException ex) {};

        return repository.save(pet);
    }

    /**
     * Gathers a list of all pets
     * @return a list of all pets in the PetsRepository
     */
    public List<Pet> listPets() {
        return repository.findAll();
    }
}

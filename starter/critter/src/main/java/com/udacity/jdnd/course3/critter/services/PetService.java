package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.PetRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
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

    /**
     * Get Pets List for a list of Pet IDs
     * @return a list of all pets in the PetsRepository
     */
    public List<Pet> findPetsById(List<Long> pedIds) {

        List<Pet> result = new ArrayList<>();
        for (int i = 0; i < pedIds.size(); i++) {
            Optional<Pet> optional = this.repository.findById(pedIds.get(i));
            if (optional.isPresent()) {
                result.add(optional.get());
            }
        }

        return result;
    }
}

package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.controllers.DTO.PetDTO;
import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.UserService;
import com.udacity.jdnd.course3.critter.services.exceptions.UserNotFoundException;
import com.udacity.jdnd.course3.critter.types.PetType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    PetService petService;
    UserService userService;

    PetController(PetService petService, UserService userService) {
        this.petService = petService;
        this.userService = userService;
    }

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) throws UserNotFoundException {
        Pet pet = new Pet();

        User owner = this.userService.findUserById(petDTO.getOwnerId());
        pet.setOwner(owner);

        pet.setId(petDTO.getId());
        pet.setType(petDTO.getType().name());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());

        Pet savedPet = this.petService.savePet(pet);
        petDTO.setId(savedPet.getId());

        return petDTO;
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping
    public List<PetDTO> getPets() {
        List<Pet> pets = this.petService.listPets();

        return pets.stream().map((Pet pet) -> {
            PetDTO petDTO = new PetDTO();
            petDTO.setId(pet.getId());
            petDTO.setName(pet.getName());
            petDTO.setType(PetType.valueOf(pet.getType()));
            petDTO.setOwnerId(pet.getOwner().getId());
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setNotes(pet.getNotes());

            return petDTO;
        }).collect(Collectors.toList());

    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        throw new UnsupportedOperationException();
    }
}

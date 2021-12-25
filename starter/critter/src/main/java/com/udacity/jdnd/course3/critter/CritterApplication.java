package com.udacity.jdnd.course3.critter;

import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.*;

/**
 * Launches the Spring application.
 */
@SpringBootApplication
public class CritterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CritterApplication.class, args);
	}

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository, PetRepository petRepository) {
        return args -> {
//            customerRepository.save(new Customer(100L, "phone", "notes"));
//            customerRepository.save(new Customer(101L, "phone", "notes"));
//            customerRepository.save(new Customer(102L, "phone", "notes"));
//            customerRepository.save(new Customer(103L, "phone", "notes"));
//            customerRepository.save(new Customer(104L, "phone", "notes"));
//
//            Pet pet1 = petRepository.save(new Pet());
//            Pet pet2 = petRepository.save(new Pet());
//            Pet pet3 = petRepository.save(new Pet());
//            List<Pet> pets = new ArrayList<>();
//            pets.add(pet1);
//            pets.add(pet2);
//            pets.add(pet3);
//            Customer customer = new Customer(104L, null, "phone", "notes", pets);
//            pet1.setOwner(customer.getUser());
//            pet2.setOwner(customer.getUser());
//            pet3.setOwner(customer.getUser());
//
//
//            customerRepository.save(customer);
        };
    }

}

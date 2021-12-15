package com.udacity.jdnd.course3.critter.model.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.udacity.jdnd.course3.critter.types.PetType;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Long id;

    private PetType type;

    @JsonProperty
    private String name;

    @ManyToOne
    private User owner;

    private LocalDate birthDate;

    private String notes;
}

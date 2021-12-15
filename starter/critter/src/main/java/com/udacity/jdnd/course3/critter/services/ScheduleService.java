package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.repositories.ScheduleRepository;

public class ScheduleService {
    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }
}

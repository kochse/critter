package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.Schedule;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.ScheduleRepository;

import java.util.List;

public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {

        this.scheduleRepository = scheduleRepository;
    }

    /**
     * Gathers a list of all schedules
     * @return a list of all schedules in the ScheduleRepository
     */
    public List<Schedule> list() {
        return scheduleRepository.findAll();
    }

    /**
     * Either creates or updates a schedule, based on prior existence of schedule
     * @param schedule A schedule object, which can be either new or existing
     * @return the new/updated schedule is stored in the repository
     */
    public Schedule saveSchedule(Schedule schedule) {
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

        return scheduleRepository.save(schedule);
    }
}

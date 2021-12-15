package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.controllers.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.controllers.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.services.UserService;
import com.udacity.jdnd.course3.critter.services.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setNotes(customerDTO.getNotes());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());

        if (customerDTO.getId() != 0) {
            try {
                User user = this.userService.findUserById(customerDTO.getId());
                user.setName(customerDTO.getName());
                customer.setUser(user);
            } catch(UserNotFoundException ex) {}
        }
        // customerDTO.getPetIds();
        // Set Pets

        Customer savedCustomer = this.userService.saveCustomer(customer);
        customerDTO.setId(savedCustomer.getId());

        return customerDTO;
        // set User
        // customerDTO.getPetIds()
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        throw new UnsupportedOperationException();
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        throw new UnsupportedOperationException();
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        throw new UnsupportedOperationException();
    }

}

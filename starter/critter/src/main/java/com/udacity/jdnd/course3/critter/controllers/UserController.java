package com.udacity.jdnd.course3.critter.controllers;

import com.udacity.jdnd.course3.critter.controllers.DTO.CustomerDTO;
import com.udacity.jdnd.course3.critter.controllers.DTO.EmployeeDTO;
import com.udacity.jdnd.course3.critter.controllers.DTO.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Employee;
import com.udacity.jdnd.course3.critter.model.persistence.Pet;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.services.PetService;
import com.udacity.jdnd.course3.critter.services.UserService;
import com.udacity.jdnd.course3.critter.services.exceptions.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
    PetService petService;

    UserController(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
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
        } else {
            User user = new User();
            user.setName(customerDTO.getName());
            customer.setUser(user);
            if (customerDTO.getPetIds() != null && customerDTO.getPetIds().size() > 0) {
                customer.setPets(petService.findPetsById(customerDTO.getPetIds()));
            }
            Customer savedCustomer = this.userService.saveCustomer(customer);
            customerDTO.setId(savedCustomer.getId());
        }

        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<Customer> customers = this.userService.listCustomers();
        return customers.stream().map((Customer customer) -> {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO.setId(customer.getId());
            if (customer.getUser() != null) {
                customerDTO.setName(customer.getUser().getName());
            }
            customerDTO.setPhoneNumber(customer.getPhoneNumber());
            customerDTO.setNotes(customer.getNotes());

            List<Pet> pets = customer.getPets();
            if (pets != null) {
                List<Long> petIds = pets.stream().map((Pet pet) -> pet.getId()).collect(Collectors.toList());
                customerDTO.setPetIds(petIds);
            }
            return customerDTO;
        }).collect(Collectors.toList());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        throw new UnsupportedOperationException();
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setDaysAvailable(employeeDTO.getDaysAvailable().toString());
        employee.setSkills(employeeDTO.getSkills().toString());

        if (employeeDTO.getId() != 0) {
            try {
                User user = this.userService.findUserById(employeeDTO.getId());
                user.setName(employeeDTO.getName());
                employee.setUser(user);
            } catch(UserNotFoundException ex) {}
        } else {
            User user = new User();
            user.setName(employeeDTO.getName());
            employee.setUser(user);
            Employee savedEmployee = this.userService.saveEmployee(employee);
            employeeDTO.setId(savedEmployee.getId());
        }

        return employeeDTO;
    }

    @GetMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        try {
            Employee employee = this.userService.findEmployeeById(employeeId);

        } catch (UserNotFoundException ex) {};

        return new EmployeeDTO();
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

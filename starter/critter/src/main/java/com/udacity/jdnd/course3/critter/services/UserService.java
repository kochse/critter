package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.persistence.Customer;
import com.udacity.jdnd.course3.critter.model.persistence.Employee;
import com.udacity.jdnd.course3.critter.model.persistence.User;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.EmployeeRepository;
import com.udacity.jdnd.course3.critter.model.persistence.repositories.UserRepository;
import com.udacity.jdnd.course3.critter.services.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public UserService(UserRepository userRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.employeeRepository = employeeRepository;
    }

    /**
     * Gathers a list of all users
     * @return a list of all user in the UserRepository
     */
    public List<User> list() {
        return userRepository.findAll();
    }

    /**
     * Gets user information by ID (or throws exception if non-existent)
     * @param id the ID number of the user to gather information on
     * @return the requested user's information
     */
    public User findUserById(Long id) throws UserNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        }
        return optional.get();
    }

    /**
     * Gets customer information by ID (or throws exception if non-existent)
     * @param id the ID number of the customer to gather information on
     * @return the requested customer's information
     */
    public Customer findCustomerById(Long id) throws UserNotFoundException {
        Optional<Customer> optional = customerRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        }
        return optional.get();
    }

    /**
     * Gets employee information by ID (or throws exception if non-existent)
     * @param id the ID number of the employee to gather information on
     * @return the requested employee's information
     */
    public Employee findEmployeeById(Long id) throws UserNotFoundException {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        }
        return optional.get();
    }

    /**
     * Either creates or updates a user, based on prior existence of user
     * @param user A user object, which can be either new or existing
     * @return the new/updated user is stored in the repository
     */
    public User saveUser(User user) {
        try {
            Optional<User> optional = userRepository.findById(user.getId());
            if (optional.isPresent()) {
                User existingUser = optional.get();
                existingUser.setName(user.getName());
                return userRepository.save(existingUser);
            }
        } catch (EntityNotFoundException ex) {};

        return userRepository.save(user);
    }

    /**
     * Either creates or updates a customer, based on prior existence of user
     * @param customer A user object, which can be either new or existing
     * @return the new/updated customer is stored in the repository
     */
    public Customer saveCustomer(Customer customer) {
        if (customer.getId() != 0) {
            try {
                Optional<Customer> optional = customerRepository.findById(customer.getId());
                if (optional.isPresent()) {
                    Customer existingCustomer = optional.get();
                    User existingUser = existingCustomer.getUser();
                    existingUser.setName(customer.getUser().getName());
                    existingCustomer.setUser(existingUser);
                    existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                    existingCustomer.setNotes(customer.getNotes());
                    // Update Pets
                    return customerRepository.save(existingCustomer);
                }
            } catch (EntityNotFoundException ex) {};
        }

        return customerRepository.save(customer);
    }

    /**
     * Either creates or updates a employee, based on prior existence of user
     * @param employee A employee object, which can be either new or existing
     * @return the new/updated employee is stored in the repository
     */
    public Employee saveEmployee(Employee employee) {
        try {
            Optional<Employee> optional = employeeRepository.findById(employee.getId());
            if (optional.isPresent()) {
                Employee existingEmployee = optional.get();
                User existingUser = existingEmployee.getUser();
                existingUser.setName(employee.getUser().getName());
                existingEmployee.setUser(existingUser);
                existingEmployee.setSkills(employee.getSkills());
                existingEmployee.setDaysAvailable(employee.getDaysAvailable());
                return employeeRepository.save(existingEmployee);
            }
        } catch (EntityNotFoundException ex) {};

        return employeeRepository.save(employee);
    }

    /**
     * Deletes a given user by ID
     * @param id the ID number of the user to delete
     */
    public void deleteUser(Long id) throws UserNotFoundException {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        } else {
            userRepository.deleteById(id);
        }
    }

    /**
     * Deletes a given customer by ID
     * @param id the ID number of the user to delete
     */
    public void deleteCustomer(Long id) throws UserNotFoundException {
        Optional<Customer> optional = customerRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        } else {
            customerRepository.deleteById(id);
        }
    }

    /**
     * Deletes a given employee by ID
     * @param id the ID number of the user to delete
     */
    public void deleteEmployee(Long id) throws UserNotFoundException {
        Optional<Employee> optional = employeeRepository.findById(id);
        if (!optional.isPresent()) {
            throw new UserNotFoundException();
        } else {
            employeeRepository.deleteById(id);
        }
    }
}

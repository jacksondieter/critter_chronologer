package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@Component
public class CustomerController {
    @Autowired
    CustomerService customerService;

    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
        List<Long> petIds = customerDTO.getPetIds();
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer, "petIds");
        return CustomerDTO.entityToDTO(customerService.createCustomer(customer, petIds));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAll().stream().map(CustomerDTO::entityToDTO).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return CustomerDTO.entityToDTO(customerService.getCustomerByPet(petId));
    }
}

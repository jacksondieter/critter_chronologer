package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.PetService;
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
        return customerToDTO(customerService.createCustomer(customer, petIds));
    }

    public List<CustomerDTO> getAllCustomers() {
        return customerService.getAll().stream().map(customer -> customerToDTO(customer)).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(@PathVariable long petId) {
        return customerToDTO(customerService.getCustomerByPet(petId));
    }

    private CustomerDTO customerToDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        System.out.println(customer);
        BeanUtils.copyProperties(customer, customerDTO, "petIds");
        customerDTO.setPetIds(customer.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
        return customerDTO;
    }
}

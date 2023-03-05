package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.generic.GenericService;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService extends GenericService<Pet> {
    @Autowired
    CustomerService customerService;

    public PetService(PetRepository repository) {
        super(repository);
    }

    public Pet createPet(Pet newPet, Long ownerId) {
        Customer customer = customerService.getById(ownerId);
        newPet.setCustomer(customer);
        Pet pet = super.create(newPet);
        customer.addPet(pet);
        customerService.update(customer);
        return pet;
    }

    public List<Pet> getPetsByOwner(Long ownerId) {
        return customerService.getById(ownerId).getPets();
    }
}

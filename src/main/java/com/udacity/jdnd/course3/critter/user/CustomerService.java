package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.generic.GenericService;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService extends GenericService<Customer> {
    @Autowired
    PetService petService;

    public CustomerService(CustomerRepository repository) {
        super(repository);
    }

    @Transactional
    public Customer createCustomer(Customer newCustomer, List<Long> petIds) {
        List<Pet> petList = new ArrayList<>();
        if (petIds != null) {
            petList = petIds.stream().map(petId -> petService.getById(petId)).collect(Collectors.toList());
        }
        newCustomer.setPets(petList);
        return super.create(newCustomer);
    }

    public Customer getCustomerByPet(Long petId){
        return petService.getById(petId).getCustomer();
    }
}

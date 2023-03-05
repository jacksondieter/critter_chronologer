package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.generic.GenericRepository;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends GenericRepository<Pet> {
}

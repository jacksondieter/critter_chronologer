package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.generic.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends GenericRepository<Pet> {}

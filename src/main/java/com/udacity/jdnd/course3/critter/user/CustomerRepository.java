package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.generic.GenericRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer> {
}

package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.generic.GenericRepository;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.user.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends GenericRepository<Schedule> {
    List<Schedule> getAllByPetsContaining(Pet pet);
    List<Schedule> getAllByEmployeesContaining(Employee employee);
}

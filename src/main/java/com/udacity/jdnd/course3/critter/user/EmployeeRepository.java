package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.generic.GenericRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface EmployeeRepository extends GenericRepository<Employee> {
    List<Employee> getAllByDaysAvailable(DayOfWeek dayAvailable);
}

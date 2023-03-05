package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.generic.GenericService;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService extends GenericService<Employee> {
    public EmployeeService(EmployeeRepository repository) {
        super(repository);
    }

    public void setEmployeeAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {
        Employee employee = getById(employeeId);
        employee.setDaysAvailable(daysAvailable);
        System.out.println(employee);
        update(employee);
    }

    public List<Employee> findEmployeesForService(LocalDate date, Set<EmployeeSkill> skills) {
        EmployeeRepository employeeRepository = (EmployeeRepository) getRepository();
        return employeeRepository.getAllByDaysAvailable(date.getDayOfWeek()).stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());

    }
}

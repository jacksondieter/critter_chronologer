package com.udacity.jdnd.course3.critter.user;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 * <p>
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@Component
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return EmployeeDTO.entityToDTO(employeeService.create(employee));
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAll().stream().map(EmployeeDTO::entityToDTO).collect(Collectors.toList());
    }

    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        return EmployeeDTO.entityToDTO(employeeService.getById(employeeId));
    }

    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return employeeService.findEmployeesForService(employeeDTO.getDate(), employeeDTO.getSkills()).stream().map(EmployeeDTO::entityToDTO).collect(Collectors.toList());
    }
}

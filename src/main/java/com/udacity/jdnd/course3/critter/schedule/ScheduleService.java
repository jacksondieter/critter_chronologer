package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.generic.GenericService;
import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleService extends GenericService<Schedule> {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    PetService petService;

    public ScheduleService(ScheduleRepository repository) {
        super(repository);
    }

    @Transactional
    public Schedule createSchedule(Schedule schedule, List<Long> employeeIds, List<Long> petIds) {
        List<Employee> employeeList = employeeIds.stream().map(employeeId-> employeeService.getById(employeeId)).collect(Collectors.toList());
        List<Pet> petList = petIds.stream().map(petId-> petService.getById(petId)).collect(Collectors.toList());
        schedule.setEmployees(employeeList);
        schedule.setPets(petList);
        return create(schedule);
    }

    public List<Schedule> getScheduleForPet(long petId){
        ScheduleRepository scheduleRepository = (ScheduleRepository) getRepository();
        Pet pet = petService.getById(petId);
        return scheduleRepository.getAllByPetsContaining(pet);
    }

    public List<Schedule> getScheduleForEmployees(long employeeId){
        ScheduleRepository scheduleRepository = (ScheduleRepository) getRepository();
        Employee employee = employeeService.getById(employeeId);
        return scheduleRepository.getAllByEmployeesContaining(employee);
    }

    public List<Schedule> getScheduleForCustomer(long customerId){
        ScheduleRepository scheduleRepository = (ScheduleRepository) getRepository();
        System.out.println(customerId);
        return petService.getPetsByOwner(customerId).stream().map(scheduleRepository::getAllByPetsContaining).flatMap(Collection::stream).collect(Collectors.toList());
    }
}

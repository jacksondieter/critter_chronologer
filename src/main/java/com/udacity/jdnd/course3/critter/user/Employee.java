package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Being;
import com.udacity.jdnd.course3.critter.generic.GenericEntity;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee extends Being implements GenericEntity<Employee> {
    @ElementCollection
    private Set<EmployeeSkill> skills = new HashSet<>();
    @ElementCollection
    private Set<DayOfWeek> daysAvailable = new HashSet<>();

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    @Override
    public String toString() {
        return super.toString() + "  " +
                "skills=" + skills +
                ", daysAvailable=" + daysAvailable +
                '}';
    }
}

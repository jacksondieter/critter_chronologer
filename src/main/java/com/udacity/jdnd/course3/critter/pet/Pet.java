package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.entity.Being;
import com.udacity.jdnd.course3.critter.generic.GenericEntity;
import com.udacity.jdnd.course3.critter.user.Customer;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Pet extends Being implements GenericEntity<Pet> {
    private PetType type;
    @ManyToOne(targetEntity = Customer.class)
    private Customer customer;
    private LocalDate birthDate;
    @Nationalized
    @Column(length = 500)
    private String notes;

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                "type=" + type +
                ", customer=" + customer.getId() +
                ", birthDate=" + birthDate +
                ", notes='" + notes + '\'' +
                '}';
    }
}

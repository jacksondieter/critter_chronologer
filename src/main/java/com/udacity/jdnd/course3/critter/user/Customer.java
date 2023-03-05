package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.entity.Being;
import com.udacity.jdnd.course3.critter.generic.GenericEntity;
import com.udacity.jdnd.course3.critter.pet.Pet;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Customer extends Being implements GenericEntity<Customer> {
    private String phoneNumber;
    @Nationalized
    @Column(length = 500)
    private String notes;
    @OneToMany(targetEntity = Pet.class)
    @JoinTable(name = "customer_pets",
            joinColumns = {@JoinColumn(name = "customer_id")},
            inverseJoinColumns = {@JoinColumn(name = "pets_id")})
    private List<Pet> pets;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet){
        pets.add(pet);
    }

    @Override
    public String toString() {
        return super.toString() + " " +
                "phoneNumber='" + phoneNumber + '\'' +
                ", notes='" + notes + '\'' +
                ", Pets='" + pets.stream().map(Pet::getId).collect(Collectors.toList()) + '\'' +
                '}';
    }
}

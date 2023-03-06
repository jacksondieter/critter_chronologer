package com.udacity.jdnd.course3.critter.pet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Long ownerId = petDTO.getOwnerId();
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet,"ownerId");
        return PetDTO.entityToDTO(petService.createPet(pet, ownerId));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return PetDTO.entityToDTO(petService.getById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAll().stream().map(PetDTO::entityToDTO).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
       return petService.getPetsByOwner(ownerId).stream().map(PetDTO::entityToDTO).collect(Collectors.toList());
    }
}

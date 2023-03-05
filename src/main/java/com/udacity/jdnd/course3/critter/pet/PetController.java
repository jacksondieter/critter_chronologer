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
        return petToDTO(petService.createPet(pet, ownerId));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        return petToDTO(petService.getById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets() {
        return petService.getAll().stream().map(pet -> petToDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
       return petService.getPetsByOwner(ownerId).stream().map(pet -> petToDTO(pet)).collect(Collectors.toList());
    }

    private  PetDTO petToDTO(Pet pet) {
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO, "customer");
        petDTO.setOwnerId(pet.getCustomer().getId());
        return petDTO;
    }
}

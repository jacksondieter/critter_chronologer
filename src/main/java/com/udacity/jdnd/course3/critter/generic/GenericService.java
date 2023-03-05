package com.udacity.jdnd.course3.critter.generic;

import com.udacity.jdnd.course3.critter.pet.Pet;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public abstract class GenericService<T extends GenericEntity<T>> {

    private final GenericRepository<T> repository;

    public GenericService(GenericRepository<T> repository) {
        this.repository = repository;
    }

    public GenericRepository<T> getRepository() {
        return repository;
    }

    public List<T> getAll(){
        return repository.findAll();
    }

    public T getById(Long id){
        return repository.findById(id).orElseThrow(UnsupportedOperationException::new);
    }

    @Transactional
    public T update(T updated){
        T dbDomain = getById(updated.getId());
        BeanUtils.copyProperties(updated, dbDomain);
        return repository.save(dbDomain);
    }

    @Transactional
    public T create(T newDomain){
        return repository.save(newDomain);
    }

    @Transactional
    public void delete(Long id){
        //check if object with this id exists
        getById(id);
        repository.deleteById(id);
    }

}

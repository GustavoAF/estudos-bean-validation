package com.estudos.bean.validation.domain.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.estudos.bean.validation.domain.model.Animal;

@Repository
public interface AnimalRepository extends CrudRepository<Animal, Long> {

}

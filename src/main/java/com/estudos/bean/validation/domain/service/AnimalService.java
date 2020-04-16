package com.estudos.bean.validation.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.bean.validation.domain.model.Animal;
import com.estudos.bean.validation.domain.repository.AnimalRepository;

@Service
public class AnimalService {
	
	@Autowired
	AnimalRepository animalRepository;
	
	public List<Animal> listar(){
		return (List<Animal>) animalRepository.findAll();
	}
	
	public Animal salvar(Animal animal) {
		return animalRepository.save(animal);
	}

}

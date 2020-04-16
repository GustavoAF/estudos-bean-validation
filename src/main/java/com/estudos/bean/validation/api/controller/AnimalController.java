package com.estudos.bean.validation.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estudos.bean.validation.domain.model.Animal;
import com.estudos.bean.validation.domain.service.AnimalService;

@RestController
@RequestMapping("/animais")
public class AnimalController {
	
	@Autowired
	AnimalService animalService;
	
	@GetMapping
	public ResponseEntity<List<Animal>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(animalService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Animal> salvar(@RequestBody @Valid Animal animal){
		return ResponseEntity.status(HttpStatus.OK).body(animalService.salvar(animal));
	}
	

}

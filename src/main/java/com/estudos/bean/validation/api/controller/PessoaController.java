package com.estudos.bean.validation.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudos.bean.validation.core.validation.Groups.CadastroPessoa;
import com.estudos.bean.validation.domain.model.Pessoa;
import com.estudos.bean.validation.domain.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Pessoa> salva(@RequestBody
										@Valid // essa anotação valida as constraints para serem processadas antes de chegar na camada de banco de dados 
										//evitando assim um erro do spring jpa.
									//	@Validated conforme decrito na classe de modelo para ativar a validação de groups é necessario informar essa anotação
										Pessoa pessoa){
		return ResponseEntity.status(HttpStatus.OK).body(pessoaService.salva(pessoa));
	}

}

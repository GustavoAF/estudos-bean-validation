package com.estudos.bean.validation.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudos.bean.validation.domain.model.Pessoa;
import com.estudos.bean.validation.domain.repository.PessoaRepository;

@Service
public class PessoaService {
		
	@Autowired
	PessoaRepository pessoaRepository;
	
	public List<Pessoa> listar() {		
		return (List<Pessoa>) pessoaRepository.findAll();
		
	}
	
	public Pessoa salva(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

}

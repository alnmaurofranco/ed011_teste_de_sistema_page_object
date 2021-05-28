package com.fatec.scel.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.scel.model.*;
import com.fatec.scel.servico.LivroServico;

@RestController
@RequestMapping("/api")
public class LivroController {
	@Autowired
	LivroServico servico;
	
	Logger logger = LogManager.getLogger(LivroController.class);
	@GetMapping("/v1/livros")
	public ResponseEntity<List<Livro>> findAll(){
		logger.info(">>>>>> 1. controller chamou servico conulta todos");
		return servico.consultaTodos();
	}
	@GetMapping("v1/livro/{id}")
	public ResponseEntity<Livro> findById(@PathVariable long id) {
		logger.info(">>>>>> 1. controller chamou servico consulta por id => " + id );
		return servico.consultaPorId(id);
	}
    
}

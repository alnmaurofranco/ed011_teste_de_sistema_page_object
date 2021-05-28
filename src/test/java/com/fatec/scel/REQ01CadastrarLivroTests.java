package com.fatec.scel;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest
class REQ01CadastrarLivroTests {
    @Autowired
    LivroRepository repository;
    Validator validator;
    ValidatorFactory validatorFactory;
    @Test
	void ct01_quando_dados_validos_retorna_cadastrado_com_sucesso() {
		//Dado  - que o atendente tem um livro não cadastrado
    	
		Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
		repository.deleteAll();
		//Quando - o atendente cadastra um livro com informações válidas
		repository.save(livro);
		//Então - os sistema valida os dados E confirma a operação
		assertEquals(1, repository.count());
	}
    @Test
    void ct02_quando_dados_validos_retorna_vazio() {
    	//Dado  - que o atendente tem um livro não cadastrado
    	Livro livro = new Livro("3333", "Teste de Software", "Delamaro");
    	//Quando - o atendente cadastra um livro com informações válidas
    	validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();
    	Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
    	//Então - os sistema valida os dados E confirma a operação
    	assertTrue(violations.isEmpty());
    }
    @Test
    void ct03_quando_titulo_branco_msg_titulo_invalido() {
    	//Dado  - que o atendente tem um livro não cadastrado
    	Livro livro = new Livro("3333", "", "Delamaro");
    	//Quando - o atendente cadastra um livro com informações invalidas
    	validatorFactory = Validation.buildDefaultValidatorFactory();
    	validator = validatorFactory.getValidator();
    	Set<ConstraintViolation<Livro>> violations = validator.validate(livro);
    	//Então - os sistema valida os dados E retorna mensagem de erro
    	assertFalse(violations.isEmpty());
    	assertEquals(1, violations.size());
    	assertEquals("O Titulo deve ter entre 1 e 50 caracteres",violations.iterator().next().getMessage());
    }
    @Test
    void ct04_quando_isbn_ja_cadastrado_retornar_violacao_de_integridade() {
    	//Dado  - que o atendente tem um livro ja cadastrado
    	repository.deleteAll();
    	Livro livro = new Livro("4444", "Teste de Software", "Delamaro");
    	repository.save(livro);
    	//Quando - o atendente registra as informações do livro e confirma a operação
    	livro = new Livro("4444", "Teste de Software", "Delamaro");
    	try {
    		repository.save(livro);
    	}catch (DataIntegrityViolationException e) {
    		assertEquals(1,repository.count());
    	}
    }

}

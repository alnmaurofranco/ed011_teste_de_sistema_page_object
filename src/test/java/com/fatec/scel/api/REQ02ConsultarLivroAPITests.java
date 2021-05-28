package com.fatec.scel.api;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class REQ02ConsultarLivroAPITests {

	@Autowired
	LivroRepository repository;
	@Autowired
	TestRestTemplate testRestTemplate;
    @BeforeAll
	void inicializa() {
		repository.deleteAll();
		Livro umLivro = new Livro("1111", "Teste de Software", "Delamaro");
		repository.save(umLivro);
		umLivro = new Livro("2222", "Engenharia de Software", "Pressman");
		repository.save(umLivro);
		List<Livro> livros = repository.findAll();
		ArrayList<Livro> lista = new ArrayList<Livro>();
		livros.forEach(cliente -> lista.add(cliente));
		lista.forEach(cli-> System.out.println("clientes nesta sessao =>" + cli.toString()));

	}

	@Test
	void ct01_quando_consulta_todos_retorna2() {
		//Dado - que existem dois livros cadastrados
        //Quando - o usuario (API) consulta todos 
		ParameterizedTypeReference<List<Livro>> tipoRetorno = new ParameterizedTypeReference<List<Livro>>() {};
		ResponseEntity<List<Livro>> resposta = testRestTemplate.exchange("/api/v1/livros", HttpMethod.GET, null, tipoRetorno);
		//Entao - retorna 2
		List<Livro> ro = resposta.getBody();
		assertEquals(2, ro.size());
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
		Livro re = new Livro("1111", "Teste de Software", "Delamaro");
		Long id = 1L;
		re.setId(id);
		Livro livro = resposta.getBody().get(0);
		assertTrue(re.equals(livro));
		
	}
	@Test
	void ct02_quando_consulta_pelo_id_retorna_os_detalhes_do_livro() {
		//Dado - que existem dois livros cadastrados
		//Quando - o usuario consulta por id
		Long id = 1L;
		ResponseEntity<Livro> resposta = testRestTemplate.getForEntity("/api/v1/livro/" + id, Livro.class);
		Livro ro = resposta.getBody();
		//Entao - retorna os detalhes do livro
		Livro re = new Livro("1111", "Teste de Software", "Delamaro");
		re.setId(id);
		assertEquals(re.getId(),ro.getId());
		assertTrue(re.equals(ro));
		
	}
}

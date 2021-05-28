package com.fatec.scel;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@SpringBootTest
class REQ02ConsultarLivroTests {
    @Autowired
    LivroRepository repository;
	@Test
	void ct01_quando_consulta_isbn_cadastrado_retorna_os_dados_do_livro() {
		//Dado que o livro está cadastrado
		repository.deleteAll();
		Livro livro = new Livro("3333", "Teste de Software","Delamaro");
		repository.save(livro);
		//Quando - o usuario consulta o livro pelo isbn
		Livro ro = repository.findByIsbn("3333");
		//Então - as informações do livro consultado são apresentadas
		assertThat(ro).isEqualTo(livro);
	}

}

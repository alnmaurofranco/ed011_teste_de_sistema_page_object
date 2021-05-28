package com.fatec.scel.servico;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fatec.scel.model.Livro;
import com.fatec.scel.model.LivroRepository;
@Service
public class LivroServicoI implements LivroServico {
	Logger logger = LogManager.getLogger(this.getClass());
	@Autowired
	LivroRepository repository;

	@Override
	public ResponseEntity<List<Livro>> consultaTodos() {
		List<Livro> livros = repository.findAll();
		return ResponseEntity.ok().body(livros);
	}

	@Override
	public ResponseEntity<Livro> consultaPorIsbn(String isbn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Livro> consultaPorId(Long id) {
		logger.info(">>>>>> 2. servico consulta por id chamado");
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Object> save(Livro livro) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

}

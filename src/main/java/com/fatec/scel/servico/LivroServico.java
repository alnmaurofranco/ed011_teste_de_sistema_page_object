package com.fatec.scel.servico;

import com.fatec.scel.model.*;
import java.util.*;

import org.springframework.http.ResponseEntity;

public interface LivroServico {
	ResponseEntity<List<Livro>> consultaTodos();

	ResponseEntity<Livro> consultaPorIsbn(String isbn);

	ResponseEntity<Livro> consultaPorId(Long id);

	ResponseEntity<Object> save(Livro livro);

	void delete(Long id);
}

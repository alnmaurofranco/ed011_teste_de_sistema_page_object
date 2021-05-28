package com.fatec.scel.api;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
@SpringBootTest
@AutoConfigureMockMvc
class REQ01CadastrarLivroAPITests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void ct01_quando_consulta_retorna_200() throws Exception {
		//Dado - que o servico esta disponivel
		//Quando - o usuario faz uma solicitação do tipo GET
		ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/livros"));
		//Entao - o servico responde HTTP 200
		resultActions.andExpect(MockMvcResultMatchers.status().isOk());
	}
	@Test
	void ct02_quando_consulta_todos_retorna2() {
		
	}

}

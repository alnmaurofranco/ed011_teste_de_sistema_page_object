package com.fatec.scel.ts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fatec.scel.po.PageAtualizarLivro;
import com.fatec.scel.po.PageCadastrarLivro;
import com.fatec.scel.po.PageExcluirLivro;
import com.fatec.scel.po.PaginaLogin;

class REQ03AtualizarLivroTSTests {

	private WebDriver driver;

	@BeforeEach
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "browserDriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ts-scel.herokuapp.com");
	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	void ct01_quando_dados_validos_retorna_livro() {
		driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.id("isbn")).click();
	    driver.findElement(By.id("isbn")).sendKeys("1234");
	    driver.findElement(By.cssSelector(".col-md-4")).click();
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).sendKeys("Kent Beck");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.id("titulo")).sendKeys("Javascript e Jquery");
	    driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
	}
	
	@Test
	void ct02_quando_consulta_livros() {
		driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    assertEquals("Lista de livros", driver.findElement(By.cssSelector(".panel-title")).getText());
	}
	
	@Test
	void ct03_quando_dados_validos_atualizar_livro() {
		driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Editar")).click();
	    driver.findElement(By.id("isbn")).click();
	    driver.findElement(By.id("isbn")).clear();
	    driver.findElement(By.id("isbn")).sendKeys("5678");
	    driver.findElement(By.id("autor")).click();
	    driver.findElement(By.id("autor")).clear();
	    driver.findElement(By.id("autor")).sendKeys("Jon Ducket");
	    driver.findElement(By.id("titulo")).click();
	    driver.findElement(By.id("titulo")).clear();
	    driver.findElement(By.id("titulo")).sendKeys("TDD - Desenvolvimento Guiado Por Testes");
	    driver.findElement(By.cssSelector(".btn")).click();
	    //assertEquals("Lista de livros", driver.findElement(By.cssSelector(".panel-title")).getText());
	}
	
	@Test
	void ct04_quando_excluir_livro_cadastrado() {
		driver.findElement(By.name("username")).click();
	    driver.findElement(By.name("username")).sendKeys("jose");
	    driver.findElement(By.name("password")).click();
	    driver.findElement(By.name("password")).sendKeys("123");
	    driver.findElement(By.cssSelector("button")).click();
	    driver.findElement(By.linkText("Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Lista de Livros")).click();
	    espera();
	    driver.findElement(By.linkText("Excluir")).click();
	}
	
	@Test
	public void ct05_page_object_quando_dados_validos_retorna_livro_cadastrado() {
		PaginaLogin paginaLogin = new PaginaLogin(driver);
		paginaLogin.login("jose", "123");
		espera();
		
		PageCadastrarLivro pageLivro = new PageCadastrarLivro(driver);
		pageLivro.cadastrar("1234", "Javascript e Jquery", "Kent Beck");
	}
	
	@Test
	public void ct06_page_object_quando_dados_validos_atualiza_livro_cadastrado() {
		PaginaLogin paginaLogin = new PaginaLogin(driver);
		paginaLogin.login("jose", "123");
		espera();
		
		PageAtualizarLivro pageAtualizarLivro = new PageAtualizarLivro(driver);
		pageAtualizarLivro.atualizar("5678", "Jon Ducket", "TDD - Desenvolvimento Guiado Por Testes");
		//assertEquals("TDD - Desenvolvimento Guiado Por Testes", pageAtualizarLivro.getResultado());
	}
	
	@Test
	public void ct07_page_object_quando_dados_validos_exclui_livro_cadastrado() {
		PaginaLogin paginaLogin = new PaginaLogin(driver);
		paginaLogin.login("jose", "123");
		espera();
		
		PageExcluirLivro pageAtualizarLivro = new PageExcluirLivro(driver);
		pageAtualizarLivro.excluir();
	}
	
	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

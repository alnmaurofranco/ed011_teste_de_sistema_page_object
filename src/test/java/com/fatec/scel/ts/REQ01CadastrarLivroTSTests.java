package com.fatec.scel.ts;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.fatec.scel.po.PageCadastrarLivro;
import com.fatec.scel.po.PaginaLogin;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class REQ01CadastrarLivroTSTests {

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
	public void ct02_quando_dados_validos_retorna_livro_cadastrado() {
		PaginaLogin paginaLogin = new PaginaLogin(driver);
		paginaLogin.login("jose", "123");
		espera();
		PageCadastrarLivro pageLivro = new PageCadastrarLivro(driver);
		pageLivro.cadastrar("3333", "Teste de Software", "Delamaro");
		assertEquals("Teste de Software", pageLivro.getResultado());
	}

	@Test
	public void ct01_quando_livro_ja_cadastrado_retorna_mensagem_de_erro() {
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		driver.findElement(By.linkText("Livros")).click();
		espera();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("1111");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Pressman");
		driver.findElement(By.id("titulo")).sendKeys("Engenharia de Software");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		espera();
		assertEquals("Livro ja cadastrado", driver.findElement(By.cssSelector(".text-danger")).getText());
	}
	
	@Test
	public void ct01_quando_dados_validos_retorna_livro_cadastrado() {
		driver.findElement(By.name("username")).click();
		driver.findElement(By.name("username")).sendKeys("jose");
		driver.findElement(By.name("password")).sendKeys("123");
		driver.findElement(By.cssSelector("button")).click();
		// seleciona a opção do menu livros
		driver.findElement(By.linkText("Livros")).click();
		espera();
		driver.findElement(By.id("isbn")).click();
		driver.findElement(By.id("isbn")).sendKeys("2222");
		driver.findElement(By.id("autor")).click();
		driver.findElement(By.id("autor")).sendKeys("Sommerville");
		driver.findElement(By.id("titulo")).click();
		driver.findElement(By.id("titulo")).sendKeys("Engenharia de Software");
		driver.findElement(By.cssSelector(".btn:nth-child(1)")).click();
		espera();
		assertEquals("Sommerville", driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(3)")).getText());
		driver.findElement(By.cssSelector("tr:nth-child(2) .delete")).click();
	}
	

	public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

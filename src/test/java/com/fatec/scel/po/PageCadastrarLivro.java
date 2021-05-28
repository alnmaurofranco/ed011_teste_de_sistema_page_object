package com.fatec.scel.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageCadastrarLivro {
	private WebDriver driver;
	private By isbnBy = By.id("isbn");
	private By autorBy = By.id("autor");
	private By tituloBy = By.id("titulo");
	private By btnCadastrarLivroBy = By.cssSelector(".btn:nth-child(1)");
	//menu
	private By btnMenuBy = By.linkText("Livros");
	
	//consulta
	private By resultado = By.cssSelector("tr:nth-child(1) > td:nth-child(3)");
	private By btnExcluirBy = By.cssSelector("tr:nth-child(2) .delete");
	
	public PageCadastrarLivro(WebDriver driver) {
		this.driver = driver;
	}
	
    public PageCadastrarLivro cadastrar (String isbn, String autor, String titulo) {
    	driver.findElement(btnMenuBy).click();
    	driver.findElement(isbnBy).sendKeys(isbn);;
    	driver.findElement(autorBy).sendKeys(autor);
    	driver.findElement(tituloBy).sendKeys(titulo);
    	driver.findElement(btnCadastrarLivroBy).click();
    	return new PageCadastrarLivro(driver);
    }
    
    public String getResultado() {
    	return driver.findElement(resultado).getText();
    }
    
    public void excluiRegistro() {
    	driver.findElement(btnExcluirBy).click();
    }
}

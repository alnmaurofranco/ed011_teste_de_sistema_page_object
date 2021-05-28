package com.fatec.scel.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageAtualizarLivro {
	private WebDriver driver;
	
	private By isbnBy = By.id("isbn");
	private By autorBy = By.id("autor");
	private By tituloBy = By.id("titulo");
	
	//menu
	private By btnMenuBy = By.linkText("Livros");
	private By btnConsultarLivroBy = By.linkText("Lista de Livros");
	private By btnEditarBy = By.linkText("Editar");
	private By btnAtualizarLivroBy = By.cssSelector(".btn");
	
	public PageAtualizarLivro(WebDriver driver) {
		this.driver = driver;
	}
	
    public PageAtualizarLivro atualizar (String isbn, String autor, String titulo) {
    	driver.findElement(btnMenuBy).click();
    	espera();
    	driver.findElement(btnConsultarLivroBy).click();
    	espera();
    	driver.findElement(btnEditarBy).click();
    	driver.findElement(isbnBy).clear();
    	driver.findElement(isbnBy).sendKeys(isbn); 
    	driver.findElement(autorBy).clear();
    	driver.findElement(autorBy).sendKeys(autor);
    	driver.findElement(tituloBy).clear();
    	driver.findElement(tituloBy).sendKeys(titulo);
    	driver.findElement(btnAtualizarLivroBy).click();
    	return new PageAtualizarLivro(driver);
    }
    
    public String getResultado() {
    	return "";
    }
    
    public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

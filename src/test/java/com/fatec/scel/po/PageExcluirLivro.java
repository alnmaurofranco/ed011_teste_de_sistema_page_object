package com.fatec.scel.po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PageExcluirLivro {
private WebDriver driver;
	
	//menu
	private By btnMenuBy = By.linkText("Livros");
	private By btnConsultarLivroBy = By.linkText("Lista de Livros");
	private By btnExcluirBy = By.linkText("Excluir");
	
	public PageExcluirLivro(WebDriver driver) {
		this.driver = driver;
	}
    
    public void excluir() {
    	driver.findElement(btnMenuBy).click();
    	espera();
    	
    	driver.findElement(btnConsultarLivroBy).click();
    	espera();
    	
    	driver.findElement(btnExcluirBy).click();
    }
    
    public void espera() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

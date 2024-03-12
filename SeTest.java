package com.qa.sedemo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeTest {
	@Test
	public void searchForPuppiesTest() {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\paulb\\MyDocs\\JavaArea\\chromedriver.exe");
		
		RemoteWebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		
		driver.get("https://www.bing.com");
		
		WebElement searchBar = driver.findElement(By.name("q"));
		searchBar.sendKeys("puppies");
		searchBar.submit();
		
		assertEquals("puppies - Search", driver.getTitle());
		
		driver.quit();
	}
}

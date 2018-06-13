package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		// Set up Chrome driver path
		WebDriverManager.chromedriver().setup();
		
		
		
		WebDriver driver = new ChromeDriver();
		//invoke selenium webdriver
		
		String url = "https://dice.com";
		driver.get(url);
		
		//Make the browser fullscreen
		driver.manage().window().maximize();
		//driver.manage().window().fullscreen();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// set universal wait time in case web page is slow
		
		
		String actualTitle= driver.getTitle();
		String expectedTitle="Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equals(expectedTitle)) {
			System.out.println("Step Pass: Dice homepage successfully loaded");
		}
		else {
			System.out.println("Step Fail: Dice homepage didn't load successfully");
			throw new RuntimeException("Step Fail. Dice homepage didn't load successfully");
		}
				

		
		String keyword="java SDET";
		driver.findElement(By.id("search-field-keyword")).clear();
		// clears the text box
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		// Finds and types java developer in the keyword box.
		// Where and What
		
		String location= "22315";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count=driver.findElement(By.id("posiCountId")).getText();
		System.out.println("Job Count: "+count);
		
		int countResult=Integer.parseInt(count.replace(",", ""));
		if(countResult>0) {
			System.out.println("Step Pass: "+"Keyword: "+keyword+" search returned "+countResult +
					" results in "+location);
		}else {
			System.out.println("Step Fail: "+"Keyword: "+keyword+" search returned "+countResult +
					" results in "+location);
		}
		
		
		driver.close();
		
		
		
		
		
		
		// Step 1. Launch browser and navigate to https://dice.com
		// Expected: dice home page should be displayed

		
		

	}

}

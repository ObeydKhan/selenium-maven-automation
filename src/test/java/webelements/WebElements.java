package webelements;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebElements {
	WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup(); // BoniGarcia

		driver = new ChromeDriver(); // Opens Chrome Browser
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // Makes the browser window fullscreen
	}

	@Test
	public void myLinks() {
		driver.get("https://github.com");
		List<WebElement> links = driver.findElements(By.xpath("//a")); // how many links on github home page
		List<String> linkTexts=new ArrayList<>();
		
		int numbersOfLinksOnGitHub = links.size();
		System.out.println("Numbers Of links: " + numbersOfLinksOnGitHub);
		
		//add each link text into a list of Strings
		for (WebElement webElement : links) {
			if(!webElement.getText().isEmpty())
			linkTexts.add(webElement.getText());
		}
	
	System.out.println(linkTexts);
		
	}
	
	@Test
	public void seleniumWebELementsForm() {
		driver.navigate().to("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		
		List <WebElement> textBoxes=driver.findElements(By.xpath("//input[@type='text']"));
		List <WebElement> dropDowns=driver.findElements(By.tagName("select"));
		List <WebElement> checkBoxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		List <WebElement> radioButtons=driver.findElements(By.xpath("//input[@type='radio']"));
		List <WebElement> submit=driver.findElements(By.xpath("//em[.='Submit']"));  // or tagName("button");
		

			
		assertEquals(textBoxes.size(), 2);
		assertEquals(dropDowns.size(),3);
		assertEquals(checkBoxes.size(), 9);
		assertEquals(radioButtons.size(), 9, "Message will show if it fails");
		assertEquals(submit.size(), 1);
		
		/*
		 * Homework:
		 * 	Loop through each inputbox and enter random names
		 *  Loop through each dropDown and randomly select by index
		 *  Loop through each checkBoxes and select each one
		 *  Loop through each radioButton and click one by one by waiting one second intervals
		 *  click all buttons
		 */
	}
	
	@Test
	public void slideShow() throws InterruptedException {
		driver.get("https://www.porsche.com/usa/modelstart/");
		List<WebElement> images = driver.findElements(By.tagName("img"));
		List<String> srcs = new ArrayList<>();
		
		for(WebElement flower: images) {
			srcs.add(flower.getAttribute("src"));
		}
		
		for (String link : srcs) {
			driver.get(link);
			Thread.sleep(1234);
		}
}
	
	@Test
	public void fillForm() throws InterruptedException {
		Faker faker=new Faker();
		
		driver.navigate().to("https://forms.zohopublic.com/murodil/form/SeleniumWebElements/formperma/eCecYgX4WMcmjxvXVq6UdhA2ABXIoqPAxnAF8H8CCJg");
		List <WebElement> textBoxes=driver.findElements(By.xpath("//input[@type='text']"));
		List <WebElement> dropDowns=driver.findElements(By.tagName("select"));
		List <WebElement> checkBoxes=driver.findElements(By.xpath("//input[@type='checkbox']"));
		List <WebElement> radioButtons=driver.findElements(By.xpath("//input[@type='radio']"));
		List <WebElement> submit=driver.findElements(By.xpath("//em[.='Submit']"));  // or tagName("button");
		
		for (WebElement webElement : textBoxes) {
			webElement.sendKeys(faker.artist().name());
		}
		
		for (WebElement webElement : dropDowns) {
			Select select=new Select(webElement);
			Random random=new Random();
			select.selectByIndex(random.nextInt(4));
		}
		for (WebElement webElement : checkBoxes) {
			webElement.click();
			//Thread.sleep(1000);
		}
		
		for (WebElement webElement : radioButtons) {
			webElement.click();
			Thread.sleep(1000);
		}
		for (WebElement webElement : submit) {
			webElement.click();
		}
		
	}
}
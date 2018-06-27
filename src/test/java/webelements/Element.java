package webelements;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Element {
	WebDriver driver;

	@BeforeClass
	public void setUp() {

		WebDriverManager.chromedriver().setup(); // BoniGarcia

		driver = new ChromeDriver(); // Opens Chrome Browser
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize(); // Makes the browser window fullscreen
	}

	@Test
	public void WebELementExamples() {

		driver.get(
				"https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ"); // Goes
																																		// to
																																		// url
		WebElement email = driver.findElement(By.name("Email"));
		String value = email.getAttribute("value");
		String maxlength = email.getAttribute("maxlength");
		String type = email.getAttribute("type");
		String tag = email.getTagName();
		boolean b = email.isEnabled();

		System.out.println("value: " + value + "\n maxlength: " + maxlength + "\n type: " + type + "\n tag :" + tag
				+ "\n isEnabled: " + b);
		
		assertEquals(value, "youremail@mail.com");
		
		email.clear();
		email.sendKeys("another@mail.com");
		
		WebElement country= driver.findElement(By.id("Address_Country"));
		Select selectCountry= new Select(country);
		
		String d=selectCountry.getFirstSelectedOption().getText();
		System.out.println(d);
		selectCountry.selectByIndex(67);
		
		
		//Lets try to generate StaleElementException
		
		WebElement cSalary=driver.findElement(By.name("Number"));
		assertEquals(cSalary.isDisplayed(), true);
		driver.get("https://www.google.com");
		//	driver.findElement(By.xpath("//em[.=' Next ']")).click();  //gives no suchElementException
		

		cSalary.sendKeys("123456");
	}
}

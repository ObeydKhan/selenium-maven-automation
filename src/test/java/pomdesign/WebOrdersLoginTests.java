package pomdesign;

import static org.testng.Assert.assertEquals;

import java.security.spec.PSSParameterSpec;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import pages.WebOrdersLoginPage;

public class WebOrdersLoginTests extends TestBase {


	
	@Ignore
	@Test
	public void positiveLoginTest() {
		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
	}


@Test (enabled=false)
public void positiveLoginUsingPOM() {

	WebOrdersLoginPage loginPage=new WebOrdersLoginPage(driver); //creates object from page class
//	loginPage.userName.sendKeys("Tester");
//	loginPage.passWord.sendKeys("test");
//	loginPage.loginButton.click();
	loginPage.login(userid, passwrd);
}


@Test
public void invalidUsernameTest() {
	WebOrdersLoginPage loginPage=new WebOrdersLoginPage(driver); //creates object from page class
//	loginPage.userName.sendKeys("Robot");
//	loginPage.passWord.sendKeys("test");
//	loginPage.loginButton.click();
	loginPage.login("Robot", passwrd);
	
	String actual=loginPage.invalidUserNameErrMsg.getText();
	String expected= "Invalid Login or Password.";
	assertEquals(actual,expected);
}
}

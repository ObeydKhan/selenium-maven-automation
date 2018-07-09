package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebOrdersLoginPage {

	public WebOrdersLoginPage(WebDriver driver) {
	PageFactory .initElements(driver, this);  //driver initializes all the variables of the this class 
	}
	
	@FindBy (id="ctl00_MainContent_username")
	public WebElement userName;
	
	@FindBy (id="ctl00_MainContent_password")
	public WebElement passWord;
	
	@FindBy (id="ctl00_MainContent_login_button")
	public WebElement loginButton;
	
	@FindBy (id="ctl00_MainContent_status")
	public WebElement invalidUserNameErrMsg;
	
	
	public void login(String uid,String pwd) {
		userName.sendKeys(uid);
		passWord.sendKeys(pwd);
		loginButton.click();
	}
	
}

package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage {

	public OrderPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "ctl00$MainContent$fmwOrder$ddlProduct")
	public WebElement product;

	@FindBy(name = "ctl00$MainContent$fmwOrder$txtQuantity")
	public WebElement quantity;

	@FindBy(name = "ctl00$MainContent$fmwOrder$txtName")
	public WebElement customerName;

	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox2")
	public WebElement street;

	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox3")
	public WebElement city;

	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox4")
	public WebElement state;

	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox5")
	public WebElement zip;

	@FindBy(xpath = "//table[@id='ctl00_MainContent_fmwOrder_cardList']//input")
	public List<WebElement> cardType;

	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox6")
	public WebElement cardNumber;
	
	@FindBy(name = "ctl00$MainContent$fmwOrder$TextBox1")
	public WebElement expirationDate;


	@FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement process;
}

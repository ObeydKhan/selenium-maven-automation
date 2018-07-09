package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {
	
	public AllOrdersPage(WebDriver driver) {
		PageFactory .initElements(driver, this);  //driver initializes all the variables of the this class 
		}

	@FindBy (xpath="//h1[.='Web Orders']")
	public WebElement webOrders;
	
	@FindBy (css=".login_info")
	public WebElement welcomeMsg;
	
	@FindBy (xpath="//h2[contains(.,'List of All Orders')]")           
	public WebElement listOfAllOrdersHeader;
	@FindBy (xpath="//a[contains(text(),'View all orders')]")
	public WebElement viewAllOrders;
	
	@FindBy (linkText="View all products")
	public WebElement viewAllProducts;
	
	@FindBy (linkText="Order")
	public WebElement orderTab;
	
	@FindBy (linkText="Logout")
	public WebElement logoutLink;
	
	@FindBy (xpath="//table[@class='SampleTable']//tr")
	public List<WebElement> ordersList;
	
	public void logout() {
		logoutLink.click();
	}

}

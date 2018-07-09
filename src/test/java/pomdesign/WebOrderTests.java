package pomdesign;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;

import pages.AllOrdersPage;
import pages.OrderPage;
import pages.ProductsPage;
import pages.WebOrdersLoginPage;

public class WebOrderTests extends TestBase {
	Map<String, String> orderFormData;

	@BeforeMethod
	public void setUpApplication() {
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		loginPage = new WebOrdersLoginPage(driver);
		allOrdersPage = new AllOrdersPage(driver);
		productsPage = new ProductsPage(driver);
		orderPage = new OrderPage(driver);
	}

	@Test(description = "Verify Labels and tab links are displayed")
	public void labelsVerification() {

		assertEquals(driver.getTitle(), "Web Orders Login", "Login page is not displayed. Application down");
//		loginPage.userName.sendKeys(userid);
//		loginPage.passWord.sendKeys(passwrd);
//		loginPage.loginButton.click();

		loginPage.login(userid, passwrd);

		assertTrue(allOrdersPage.webOrders.isDisplayed(), "WebOrders is not displayed");
		assertTrue(allOrdersPage.listOfAllOrdersHeader.isDisplayed(), "List Of All Orders is not displayed");
		assertTrue(allOrdersPage.viewAllProducts.isDisplayed());
		assertEquals(allOrdersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, " + userid + "!");
		assertTrue(allOrdersPage.orderTab.isDisplayed());

	}

	@Test(description = "Verify default Products and Prices")
	public void availableProductsTest() {

		loginPage.login(userid, passwrd);
		assertEquals(driver.getTitle(), "Web Orders", "Login page is not displayed. Application down");
		allOrdersPage.viewAllProducts.click();

		List<String> expProducts = Arrays.asList("MyMoney", "FamilyAlbum", "ScreenSaver");
		List<String> actProducts = new ArrayList<>();

		productsPage.productNames.forEach(eachItem -> actProducts.add(eachItem.getText()));

		assertEquals(actProducts, expProducts);

		for (WebElement row : productsPage.productsRows) {
			String[] prodData = row.getText().split(" ");

			switch (prodData[0]) {
			case "MyMoney":
				assertEquals(prodData[1], "$100");
				assertEquals(prodData[2], "8%");
				break;

			case "FamilyAlbum":
				assertEquals(prodData[1], "$80");
				assertEquals(prodData[2], "15%");
				break;

			case "ScreenSaver":
				assertEquals(prodData[1], "$20");
				assertEquals(prodData[2], "10%");
				break;
			}
		}
	}

	@Test(description = "Fill Form and Verify data matches after submission")
	public void formTest() {

		loginPage.login(userid, passwrd);
		allOrdersPage.orderTab.click();
		assertEquals(driver.getTitle(), "Web Orders", "Login page is not displayed. Application down");

		orderFormData = new HashMap();
		fillOrderForm();
		orderPage.process.click();
		allOrdersPage.viewAllOrders.click();

		List<WebElement> myOrder = null;

		for (WebElement each : allOrdersPage.ordersList) {
			if (each.getText().contains(orderFormData.get("customerName"))) {
				myOrder = driver.findElements(By.xpath("//table[@class='SampleTable']//tr[contains(.,'"
						+ orderFormData.get("customerName") + "')]/td"));
				break;
			}
			myOrder = null;
		}

		if (myOrder != null) {  //assertEquals doesnt work for Set, as it doesn't take Un-Orderedness of Sets  into consideration.
			
			SortedSet<String> actual = new TreeSet();
		
			myOrder.forEach( each ->{ 
				if(!each.getText().isEmpty())
				actual.add(each.getText());
			}
			);
			
			SortedSet<String> expected = new TreeSet(orderFormData.values());
			assertEquals(actual, expected);
		} else {
			fail("myOrder is Empty");
		}



	}

	@AfterMethod
	public void logout() {
		 allOrdersPage.logout();
	}

	public void fillOrderForm() {

		Random random = new Random();
		Faker faker = new Faker();

		Select select = new Select(orderPage.product); // select a random product from drop down
		int selectByindex = random.nextInt(select.getOptions().size());
		select.selectByIndex(selectByindex);
		orderFormData.put("productName", select.getFirstSelectedOption().getText());

//		switch(selectByindex) {
//		case 0:
//		select.selectByVisibleText("MyMoney");
//		orderFormData.put("productName", "MyMoney");
//		break;
//		
//		case 1:
//			select.selectByVisibleText("FamilyAlbum");
//			orderFormData.put("productName", "FamilyAlbum");
//			break;
//			
//		case 2:
//			select.selectByVisibleText("ScreenSaver");
//			orderFormData.put("productName", "ScreenSaver");
//			break;
//
//		}

		String quanty = String.valueOf(random.nextInt(1000000));
		orderPage.quantity.clear();
		orderPage.quantity.sendKeys(quanty); // sends a random number to quantity
		orderFormData.put("quantity", quanty);

		String name = faker.name().fullName();
		orderPage.customerName.sendKeys(name); // sends a random number to quantity
		orderFormData.put("customerName", name);

		String streetAddress = faker.address().streetAddress();
		orderPage.street.sendKeys(streetAddress); // text sent to text boxes using faker
		orderFormData.put("streetAddress", streetAddress);

		String cityName = faker.address().cityName();
		orderPage.city.sendKeys(cityName);
		orderFormData.put("cityName", cityName);

		String state = faker.address().state();
		orderPage.state.sendKeys(state);
		orderFormData.put("state", state);

		String zipcode = faker.number().digits(5);
		orderPage.zip.sendKeys(zipcode);
		orderFormData.put("zipcode", zipcode);

		int cardTypeByIndex = random.nextInt(3);
		String type;
		String creditCardNumber;
		CreditCardType c;

		switch (cardTypeByIndex) {
		case 0:
			type = "Visa";
			orderFormData.put("creditCardType", type);
			orderPage.cardType.get(cardTypeByIndex).click();
			c = CreditCardType.VISA;
			creditCardNumber = faker.finance().creditCard(c).replaceAll("-", "");
			orderPage.cardNumber.sendKeys(creditCardNumber);
			orderFormData.put("creditCardNumber", creditCardNumber);
			break;

		case 1:
			type = "MasterCard";
			orderFormData.put("creditCardType", type);
			orderPage.cardType.get(cardTypeByIndex).click();

			c = CreditCardType.MASTERCARD;
			creditCardNumber = faker.finance().creditCard(c).replaceAll("-", "");
			orderPage.cardNumber.sendKeys(creditCardNumber);
			orderFormData.put("creditCardNumber", creditCardNumber);
			break;

		case 2:
			type = "American Express";
			orderFormData.put("creditCardType", type);
			orderPage.cardType.get(cardTypeByIndex).click();

			c = CreditCardType.AMERICAN_EXPRESS;
			creditCardNumber = faker.finance().creditCard(c).replaceAll("-", "");
			orderPage.cardNumber.sendKeys(creditCardNumber);
			orderFormData.put("creditCardNumber", creditCardNumber);
			break;

		}

//		Date date=faker.date().future(100*365, TimeUnit.DAYS);
//		int temp=date.getMonth();
//		String month=temp<10? "0"+temp: String.valueOf(temp);
//		
//		String expDate=month+"/"+date.getYear()%100;

		int temp = random.nextInt(2); // Generates the left digit of month i.e. 0 or 1
		String month = temp == 1 ? temp + "" + random.nextInt(3) : temp + "" + (random.nextInt(9) + 1);
		String year = String.valueOf(random.nextInt(82) + 18);
		String expDate = month + "/" + year;
		orderPage.expirationDate.sendKeys(expDate);
		orderFormData.put("Expiry date", expDate);
		
		String[] currentDateArray=LocalDate.now().toString().split("-");
		String myDate=currentDateArray[1]+"/"+currentDateArray[2]+"/"+currentDateArray[0];
		orderFormData.put("currentDate", myDate);
//		orderFormData.put("currentDate", "07/09/2018");
	}
}

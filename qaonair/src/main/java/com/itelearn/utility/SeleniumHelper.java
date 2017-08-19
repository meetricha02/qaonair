	package com.itelearn.utility;

	import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.itelearn.properties.PropertyFileReader;


	public class SeleniumHelper {
		public static WebDriver driver;
		String url;
		ExcelUtility et= new ExcelUtility();
		
		//public Logger log=LogManager.getLogger(SeleniumHelper.class.getName());

		@Test
		public void execute(String keyword, String fdata, String fElement, String fElementType) throws IOException, InterruptedException{
			switch(keyword) {

			case "openBrowser":
				openBwsr();
				break;

			case "navigateBrowser":
				navigateBwsr();
				break;

			case "clickLink":
				clicklnk(fElement,fElementType);
				break;
			
			case "clickButton":
				clickbtn(fElement,fElementType);
				break;

			case "typeText":
				typTxt(fElement, fdata, fElementType);
				break;

			case "verifytext":
				verifytxt(fElement, fdata, fElementType );
				break;



			case "verifyTitle":
				verifyPageTitle(fdata);
				break;


			case "maximizeWindow":
				maximizeWindow();
				break;


			case "mouseHover":
				mouseHover(fElement,fdata,fElementType);
				break;



			case "wait":
				wtTime(40);
				break;
			}

		}	


		@Test
		// To check the element type//
		public WebElement elementTyp(String fElement, String fElementType){
			if(fElementType.equalsIgnoreCase("linkText")){
				return driver.findElement(By.linkText(fElement));
			}
			else if(fElementType.equalsIgnoreCase("cssSelector")){ 
				return driver.findElement(By.cssSelector(fElement)); 
			}
			else {
				return driver.findElement(By.xpath(fElement)); 
			}
			
		}


		
		@Test
		public void openBwsr() throws IOException{
			System.out.println("-----------Opening the Browser---------");
			String setName= PropertyFileReader.getProperty("setUpName");
			System.out.println(setName);
			String setPath= PropertyFileReader.getProperty("setUpPath");
			System.out.println(setPath);
			System.setProperty(setName, setPath);
			driver= new ChromeDriver();
			System.out.println("Opening Chrome browser");
		}


		@Test
		public void navigateBwsr() throws IOException{
			System.out.println("-----------Navigate to the URL---------");
			String url = PropertyFileReader.getProperty("URL");
			System.out.println("Navigating to url ");
			driver.navigate().to(url);

		}

		@Test
		public void clickbtn(String vElement,String vType){
			System.out.println("-----------Clicking the button--------- for element " + vElement);
		
			if(vElement != null && !vElement.trim().equals("-")){
				driver.findElement(By.xpath(vElement)).click();
			}
		}




		//Getting the webdriver for FreelProjectsNoLogin Class Screenshot method//
		public WebDriver getDriver() {
			return driver;
		}

//
//		@Test
//		public void verifytxt(String vElement, String vdata, String vType){
//			String expectedtext="Please enter a valid email address.";
//			System.out.println("-----------Verifying the text--------- for element " + vElement);
//			String actualText= driver.findElement(By.xpath(vElement)).getText();
//			if(actualText.equals(expectedtext)){
//				System.out.println("The error message is verified, Test Passes!!!!");
//			}
//		}
//

		@Test
		public void maximizeWindow() throws InterruptedException{
			System.out.println("-----------Maximizing the Window---------");
			driver.manage().window().maximize();
		}

		@Test
		public void mouseHover(String vElement, String vdata, String vType){
			System.out.println("----------MouseHover-------------");
			Actions act= new Actions(driver);
			WebElement we= elementTyp(vElement,vType);
			act.moveToElement(we).click().build().perform();
		}

		@Test
		public void typTxt(String vElement, String vdata, String fElementType){
			System.out.println("-----------typing the text---------");
			WebElement we= elementTyp(vElement, fElementType);
			we.sendKeys(vdata);
		}

		@Test
		public void clicklnk(String vElement, String fElementType){
			System.out.println("-----------Clicking the Link---------"+vElement);
			System.out.println("The element type is:"+ fElementType);
			if(vElement != null && !vElement.trim().equals("-")){
				WebElement element= elementTyp(vElement, fElementType);
				element.click();
			}

		}	


	
		@Test
		public void wtTime(long fdata){
			System.out.println("-----------Introducing wait---------");{
				driver.manage().timeouts().implicitlyWait(fdata, TimeUnit.SECONDS);
			}
		}




		@Test
		public void verifytxt(String vElement, String fdata, String vType){  
			System.out.println("-----------Verifying--------------------");
			WebElement we=  elementTyp(vElement, vType);
			String actual=		we.getText();
			System.out.println("Actual text is:"+ actual);
			System.out.println("Expected text is:"+ fdata);

			System.out.println(actual);
			Assert.assertEquals(actual, fdata);
		}

		@Test
		public void verifyPageTitle(String fdata){
			System.out.println("-----------Verifying Page title--------------------");
			String actual=driver.getTitle();
			System.out.println();
			Assert.assertEquals(actual, fdata);
		}
	}	



	

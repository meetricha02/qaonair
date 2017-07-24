package com.qaonair.automation.utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeleniumHelper {
	
WebDriver driver;
String url;


@Test
public void execute(String keyword, String fdata, String fElement) throws IOException, InterruptedException{
	switch(keyword) {
	
	case "openBrowser":
openBwsr();
	break;
	
	case "navigateBrowser":
	navigateBwsr();
	break;
	
	case "clickLink":
	clicklnk(fElement);
	break;
	

	case "clickButton":
	clickbtn(fElement);
	break;
	
	case "typeText":
	typTxt(fElement, fdata);
	break;
	
	case "maximizeWindow":
		maximizeWindow();
		break;
		
	case "wait":
		wtTime(40);
		break;
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
	public void clickbtn(String vElement){
	System.out.println("-----------Clicking the button--------- for element " + vElement);
		if(vElement != null && !vElement.trim().equals("-")){
		driver.findElement(By.xpath(vElement)).click();
		}
	}

	@Test
	public void maximizeWindow() throws InterruptedException{
		System.out.println("-----------Maximizing the Window---------");
	driver.manage().window().maximize();
}
	
	@Test
	public void typTxt(String vElement, String vdata){
		System.out.println("-----------typing the text---------");
		driver.findElement(By.xpath(vElement)).sendKeys(vdata);
		
	}
	@Test
	public void clicklnk(String vElement){
		System.out.println("-----------Clicking the Link---------"+vElement);
		if(vElement != null && !vElement.trim().equals("-")){
			driver.findElement(By.xpath(vElement)).click();;
			//driver.findElement(By.xpath(vElement)).click();
		}
		
	}	
		@Test
		public void wtTime(long fdata){
			System.out.println("-----------Introducing wait---------");{
			driver.manage().timeouts().implicitlyWait(fdata, TimeUnit.SECONDS);
		}
		
		
	}
}
	
	
	
	
	


package eCommerce.qaonair;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qaonair.automation.utility.ExcelUtility;
import com.qaonair.automation.utility.SeleniumHelper;


public class AppNew {

public String keyword;
String TCName;


//String[][] testCases = new String[2][3];


	@BeforeTest
	public void projectSetUp() throws IOException{
		SeleniumHelper sh= new SeleniumHelper();
	//	sh.openBwsr();
	//	sh.navigateBwsr();
	//	sh.maximizeWindow();
	}
	
	
	@Test
	public void freelSignUp() throws IOException{
		System.out.println("+++++++++++++++++Entering freelSignUp method++++++++++");
		ExcelUtility x= new ExcelUtility();
		SeleniumHelper sHelp= new SeleniumHelper();
		
	System.out.println("$$$$$$$$$$$$$  Reading the TestCase Sheet  $$$$$$$$$$$$$$$");
		String[][] xlTC= x.readTestcases();
		int TCrows= x.rTC;
		for(int k=1;k<TCrows;k++){
			 TCName=	xlTC[k][1];
		 System.out.println("The test case name are:"+ TCName);
		 System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%");
		String TC1="TC_FreeL_SignUp_001";
		String TC2="TC_FreeL_SignUp_002";

		
	System.out.println("$$$$$$$$$$$$$  Reading the TestStep Sheet  $$$$$$$$$$$$$$$");
		 String[][] xlTestSteps=x.readXL();
		if(TCName.equals(TC1)){
		int	rows= x.nRows;
		for (int k1=1;k1<rows;k1++){
			String	vTSID=xlTestSteps[k1][2];
			keyword= xlTestSteps[k1][5];
			String vEID= xlTestSteps[k1][8];
			String vTData=xlTestSteps[k1][11];

	System.out.println("++++++++The number of rows in TestSteps sheet are++++++++:"+ rows);
	System.out.println("The TestStep ID is:"+ vTSID);
	System.out.println("The Keyword is:"+ keyword);
	System.out.println("The Element ID is:"+ vEID);
	System.out.println("The TestStep data is:"+ vTData);
sHelp.execute(keyword, vTData, vEID);
			}
		}
	}
//	
//	//*********Reading the EID sheet*********//
//	System.out.println("$$$$$$$$$$$$$  Reading the EID Sheet  $$$$$$$$$$$$$$$");
//
//	String[][] xlEID = x.readElementID();
//	int erows= x.rEID;
//	for(int l=1;l<erows ;l++){
//	String eName= xlEID[l][1];
//	String eID= xlEID[l][3];
//	System.out.println("++++++++The no rows are++++++++:"+ erows);
//	System.out.println("The Element ID is:"+ eID);
//	System.out.println("The Element Name is:"+ eName);
//	}
//	
//	
//	System.out.println("$$$$$$$$$$$$$  Reading the TestData Sheet  $$$$$$$$$$$$$$$");
//	String[][] sheetData = x.readSheetData();
//	int drows= x.rdata;
//	for(int m=2;m<drows ;m++){
//	String dataName= sheetData[m][1];
//// get values from column 1, 2 or 3
//	System.out.println("++++++++The no rows are++++++++:"+ drows);
//	System.out.println("The Test Data name is:"+ dataName);	
//	
//	}
	
	
	//sHelp.execute(keyword, xlEID, sheetData);    //????????????????????????????/
	System.out.println("+++++++++Exiting freelSignUp method++++++++++++");	
		}
	}



////Signup link on home page
//driver.findElement(By.xpath(".//a[text()='SIGN UP']	")).click();
////sign up button under freelancer
//driver.findElement(By.xpath(".//div[@class='register-freelancer']/a")).click();
////firstname edit box on Freelancer Signup page
//driver.findElement(By.id("first_name")).sendKeys("abc");
////lastname edit box on Freelancer Signup page
//driver.findElement(By.id("last_name")).sendKeys("abc");
//driver.findElement(By.id("user_email")).sendKeys("abc@gmail.com");
//driver.findElement(By.id("user_pass")).sendKeys("123");
//driver.findElement(By.id("repeat_pass")).sendKeys("123");
////driver.findElement(By.tagName("button")).click();
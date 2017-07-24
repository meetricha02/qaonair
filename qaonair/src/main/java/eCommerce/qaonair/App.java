package eCommerce.qaonair;

import java.io.IOException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.qaonair.automation.utility.ExcelUtility;
import com.qaonair.automation.utility.SeleniumHelper;


public class App {
WebDriver driver;
public String keyword;
String TCName;

public static Logger log= LogManager.getLogger(App.class.getName());
	
	
	@Test
	public void freelSignUp() throws IOException, InterruptedException{
		log.info("**************Entering freelSignUp method**************"); 

		
				
		
		
		ExcelUtility excelUtility= new ExcelUtility();
		SeleniumHelper sHelp= new SeleniumHelper();

		log.info("----------  Reading the TestCases Sheet  ------------");
		String[][] xlTC= excelUtility.readTestcases();
		int TCrows= excelUtility.rTC;
		for(int k=1;k<TCrows;k++){
			TCName=	xlTC[k][1];
			log.info("!!!!!!!!!!!!The test case name is!!!!!!!!!:"+ TCName);
			
			String TC1="TC_FreeL_SignUp_001";
			String TC2="TC_FreeL_SignUp_002";



			log.info("--------  Reading the TestSteps Sheet ----------");
			String[][] xlTestSteps=	excelUtility.readTestSteps();
			System.out.println(TCName.equals(TC1)+"?????????????????????");
			
			if(TCName.equals(TC1)){
				int	NewRows= excelUtility.nRows;
				for (int k1=1;k1<NewRows;k1++){
					String	vTCID=xlTestSteps[k1][1];
					if(TCName.equals(vTCID)){
					keyword= xlTestSteps[k1][5];
					String vEID= xlTestSteps[k1][8];
					String vTData=xlTestSteps[k1][11];

					System.out.println("++++++++The number of rows in TestSteps sheet are++++++++:"+ NewRows);
					System.out.println("The TestCase ID is:"+ vTCID);
					System.out.println("The Keyword is:"+ keyword);
					System.out.println("The Element ID is:"+ vEID);
					System.out.println("The TestStep data is:"+ vTData);
					sHelp.execute(keyword, vTData,vEID);
				}
				}
			}
			
		}

		System.out.println("+++++++++Exiting freelSignUp method++++++++++++");	
	}

	// @AfterMethod
	//	public void screenShot(ITestResult result){
	//		if(ITestResult.FAILURE==result.getStatus()){
	//			Utility.captureScreenshot(driver, result.getStatus());
	//		}
	//}

}



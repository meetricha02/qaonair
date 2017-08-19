package com.itelearn.qaonair.testbase;


import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.itelearn.properties.PropertyFileReader;
import com.itelearn.utility.ExcelUtility;
import com.itelearn.utility.SeleniumHelper;

public class TestBaseTest {

	//String keyword4, vEID4, vTDataName4;
	//public String type;

	private String types;
	String TestCaseName;
	String RunMode;
	String xkeyword, xEID, vTDataNames;


	SeleniumHelper sh= new SeleniumHelper();
	ExcelUtility eu= new ExcelUtility();


	@BeforeClass
	//Setting the Chrome Browser property
	public static void settingBrowserProperties(){
		String prop1=	PropertyFileReader.getProperty("setUpName");
		String prop2= PropertyFileReader.getProperty("setUpPath");

		System.setProperty(prop1, prop2);
	}


	@Test
	public void basetest() throws IOException, InterruptedException{
		System.out.println("--------------Reading test cases----------------");

		String[][] testCaseSheet= eu.readExcel("TestCases");
		int sheetRowNum= eu.sheetRows;

		String[][] testStepSheet	= eu.readExcel("TestSteps");

		for(int k=1;k<sheetRowNum;k++){
			TestCaseName=	testCaseSheet[k][1];
			RunMode= testCaseSheet[k][3];
			//System.out.println("The test case name is:"+ testCaseSheet);


			if(RunMode.equalsIgnoreCase("Y")){


				System.out.println("--------  Reading the TestSteps Sheet ----------");


				int	TestStepRows= testStepSheet.length;

				for (int y=1;y<TestStepRows;y++){
					String	vTCID=testStepSheet[y][1];


					if(TestCaseName.equals(vTCID)){
						xkeyword= testStepSheet[y][5];
						xEID= testStepSheet[y][8];
						vTDataNames=testStepSheet[y][11];



						System.out.println("------------------Reading the EID sheet-------------");
						String[][] EIDdataSheet= eu.readExcel("EID");

						for(int m=1; m<eu.sheetRows;m++){
							if(xEID!=null && xEID.equalsIgnoreCase(EIDdataSheet[m][3])){	
								setTypes(EIDdataSheet[m][2]);                      ////??????????
							}
							//System.out.println("The EID values are++++++++++++++:"+ type);
						}
						sh.execute(xkeyword,vTDataNames,xEID,getTypes());
					}
				}
			}
		}
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

@AfterClass
public void testDown(){
	sh.getDriver().quit();
}

}

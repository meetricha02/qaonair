
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.itelearn.properties.PropertyFileReader;
import com.itelearn.utility.ExcelUtility;
import com.itelearn.utility.SeleniumHelper;


public class practiceDDFTest2 {
	//Data driving using Excel
	//Input: Data from Excel sheet "Sheet1"

	String key, eid, type, value;
	WebDriver driver;


	@BeforeTest
	// Setting the Chrome Browser property
	public void settingBrowserProperties() {
		String prop1 = PropertyFileReader.getProperty("setUpName");
		String prop2 = PropertyFileReader.getProperty("setUpPath");
		System.setProperty(prop1, prop2);
	}

	@DataProvider(name="testdata")
	public Object[][] readExceltest() throws IOException{
		ExcelUtility e= new ExcelUtility();
		String[][] sheetdata=	e.readExcel("Sheet1");

		int rows= e.sheetRows;
		int cols= e.sheetCols;
//Copying the data from last row and 0th col in first cell
		sheetdata[0][0]= sheetdata[2][0];
		System.out.println("Sheet1 data sheetdata[0][0] is :"+ sheetdata[0][0]);
////Copying the data from last row and 1st col in first cell
		sheetdata[0][1]= sheetdata[2][1];
		System.out.println("Sheet1 data sheetdata[0][1] is :"+ sheetdata[0][1]);
//New array with rows equal to sheetdata rows-1, as we will ignore the last row later(as it is already copied to first)
		String[][] userData= new String[sheetdata.length-1][cols];

		for(int a=0;a<sheetdata.length-1;a++){
			for(int b=0;b<cols;b++){
				userData[a][b]= sheetdata[a][b];
				System.out.println("user data is :"+ userData[a][b]);
			}
		}
				return userData;
	}


	@Test(dataProvider = "testdata")
	public void loginDataSet( String Uname, String Pwd) throws IOException, InterruptedException {

		SeleniumHelper 	 s = new SeleniumHelper();
		ExcelUtility e= new ExcelUtility();

		String Testcase = "TC_valid_dataset_001";

		String[][] testSteps = e.readExcel("TestSteps");
		int rows = e.sheetRows;

		for (int a = 1; a < rows; a++) {
			String TCID = testSteps[a][1];
			value = testSteps[a][11];
			type= testSteps[a][12];
			key = testSteps[a][5];
			//System.out.println("Keyword is :-----------" + key);
			eid = testSteps[a][8];

			//If the value of the cell is "Username", then get the data from data providers//
			if (value.equals("Username")) {

				System.out.println("UserName is :" + value);
				value = Uname;
			}

			//If the value of the cell is "Password", then get the data from data providers//

			if (value.equals("Password")) {

				System.out.println("Password is :" + value);

				value = Pwd;
			}
			if (TCID != null && TCID.equalsIgnoreCase(Testcase)) {
				//Value from the data providers(Uname/Pwd) passed to the execute method in Selenium Helper Class//
				s.execute(key, value, eid, type);
			}
		}
	}

	//@AfterMethod
	public void teardown(){
		driver.close();
	}
}



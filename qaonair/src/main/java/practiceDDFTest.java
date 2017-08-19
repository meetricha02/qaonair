
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.itelearn.properties.PropertyFileReader;
import com.itelearn.utility.ExcelUtility;
import com.itelearn.utility.SeleniumHelper;


public class practiceDDFTest {
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

				for(int a=0;a<rows;a++){
					for(int b=0;b<cols;b++){
					System.out.println("Sheet1 data is :"+ sheetdata[a][b]);
		
				}
				}
				
//		String[][] userdata= new String[rows][cols];
//
//		for(int m=0;m<rows;m++){
//			for(int n=0;n<cols;n++){
//				String val	=sheetdata[m+1][n];
//				//		}
//				//	}
//
//				for(int k=1;k<rows;k++){
//					for(int l=0;l<cols;l++){
//						userdata[m][n]= val;
//						System.out.println("userdata is :--------"+userdata[k][l] );
//					}
//				}
//			}
//		}
		return sheetdata;
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



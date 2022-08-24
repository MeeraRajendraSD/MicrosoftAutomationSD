package microsoft.qa.testcases;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import microsoft.qa.base.TestBase;
import microsoft.qa.pages.AplicacionesPage;
import microsoft.qa.pages.HomePage;
import microsoft.qa.pages.ProductDetailsPage;
import microsoft.qa.pages.SearchResultPage;
import microsoft.qa.pages.WindowsPage;

public class SearchValidation extends TestBase{
	
	HomePage homepg;
	WindowsPage windowspg;
	SearchResultPage resultpg;
	AplicacionesPage aplicacionespg;
	ProductDetailsPage productDetailspg;
	
	public SearchValidation() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialize();
		homepg= new HomePage();	
	}
	
	@Test (dataProvider="getData")
	public void validateSearch(HashMap <String,Object> dataMap) {
		windowspg= homepg.clickOnWindows();
		String searchData = null;
		for (Map.Entry<String,Object> m:dataMap.entrySet() ) {
			System.out.println(m.getKey()+" : "+ m.getValue());
			 searchData= (String) m.getValue();
		}
		
		resultpg= windowspg.performSearch(searchData);
		resultpg.openComprarTab();
		aplicacionespg = resultpg.clickOnAplicacionesbutton();
		
		// find total number of apps on the first 3 pages and print names of all the apps on the first 3 pages
		
		ArrayList<String> allAppNames= new ArrayList<String>();		
		int appCount=0;
		
		for (int pageNo=1; pageNo<=3; pageNo++) {
			allAppNames= aplicacionespg.getNameOfAllProductsOnPage();
			appCount= appCount+ allAppNames.size();
			System.out.println("");
			System.out.println("===================");
			System.out.println("Apps on page " + pageNo);
			System.out.println("===================");
			System.out.println("");
			
			for(String appName : allAppNames) {
				System.out.println(appName);
				}
			if(pageNo!=3) {
				aplicacionespg.moveToPage(pageNo+1);	
			}else {
				aplicacionespg.moveToPage(1);
			}
			
		}
		System.out.println("");
		System.out.println("Total number of products on the first 3 pages = " + appCount);
		
		//Find price of first non free item
		
		Hashtable<String,String> appPricelist= aplicacionespg.getPriceOfAllAppsOnPage();
		
		Set<String> app= appPricelist.keySet();
		
		boolean nonZeroItemFound= false;
		String nonZeroItemName= "";
		String appPrice= "";
		ArrayList<String> allAppNamesOnpg1= aplicacionespg.getNameOfAllProductsOnPage();
		for (String item: allAppNamesOnpg1) {
			appPrice= appPricelist.get(item);
			System.out.println(item+" : "+appPrice);
			if (!(appPrice.contains("Gratis"))) {
				nonZeroItemFound= true;
				nonZeroItemName= item;
				break;
			}
			
		}
		
		System.out.println("First non zero app price= "+ appPrice);
		System.out.println(nonZeroItemName);
		productDetailspg= aplicacionespg.selectAndOpenApp(nonZeroItemName);
		
		String productDetailPrice= productDetailspg.getProductPrice();
		
		Assert.assertEquals(appPrice, productDetailPrice,"Price is not matching");
			
		
	}
	
	@DataProvider
	public Object[] getData() throws StreamReadException, DatabindException, IOException{
		HashMap <String,Object> dataMap1= new ObjectMapper()
				.readValue(new File("/Users/kashyapbhatt/eclipse-Selenium-Java-workspace/MicrosoftProject/src/main/java/microsoft/qa/testdata/testdata.json"),
						new TypeReference<HashMap<String,Object>>(){});
		return new Object[] {dataMap1};
	}
	
	
	
	@AfterMethod 
	public void tearDown () {
		driver.close();
	}

	
	
	
	
	
	
	
	

}

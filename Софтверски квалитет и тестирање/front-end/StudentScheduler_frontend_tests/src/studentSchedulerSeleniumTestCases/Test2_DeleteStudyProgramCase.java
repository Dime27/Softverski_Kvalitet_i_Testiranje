package studentSchedulerSeleniumTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Ja brise prvata studiskata programa od listata so studiski programi i potoa proveruva dali taa seuste se naogja vo listata
 */
public class Test2_DeleteStudyProgramCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testDeleteStudyProgram() throws Exception {
		driver.get("http://localhost:3000/");
		Thread.sleep(3000);
		
		String firstStudyProgramName = driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("studyProgramItemNames")).get(0).getText();		
		WebElement btnDelete = driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("btn-delete")).get(0);
		btnDelete.click();
		Thread.sleep(3000);
		
		List<WebElement> elementsAfterDelete = driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("studyProgramItemNames"));
		
	    boolean studyProgramNameDoesntExist = true;
	    for(WebElement name : elementsAfterDelete) {
	    	if(name.getText().equals(firstStudyProgramName)) {
	    		studyProgramNameDoesntExist = false;
	    		break;
	    	}	    			    		
	    }	  
	    Thread.sleep(3000);
	    Assert.assertTrue(studyProgramNameDoesntExist);	    
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

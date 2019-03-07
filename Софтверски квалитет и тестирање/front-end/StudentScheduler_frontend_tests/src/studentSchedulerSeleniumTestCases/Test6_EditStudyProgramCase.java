package studentSchedulerSeleniumTestCases;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Test za editiranje na prvata studiska programa od listata so studiski programi. Najprvo i go menuva imeto 
 * a potoa po klik na kopceto submit proveruva dali prvata stavka od listata e imenuvana so promenetoto ime.
*/
public class Test6_EditStudyProgramCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testEditStudyProgram() throws Exception {
		String newStudyProgramName = "SKIT";
		
		driver.get("http://localhost:3000/");
		Thread.sleep(5000);
		WebElement btnEdit = driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("btn-edit")).get(0);
		btnEdit.click();
		
		driver.findElement(By.id("studyProgramsListCard")).findElement(By.name("studyProgram")).sendKeys(newStudyProgramName);
		driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("btn-submit")).get(0).click();
	    Thread.sleep(5000);
	    
	    boolean isStudyProgramNameChanged = false;
	    List<WebElement> itemNames = driver.findElements(By.className("studyProgramItemNames"));
	    for(WebElement name : itemNames) {
	    	if(name.getText().equals(newStudyProgramName)) {
	    		isStudyProgramNameChanged = true;
	    		break;
	    	}	    			    		
	    }	    
	    Assert.assertTrue(isStudyProgramNameChanged);	    
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

package studentSchedulerSeleniumTestCases;

import java.util.concurrent.TimeUnit;
import java.util.List;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


/*
 * Test za dodavanje na nova studiska programa. Prvo ja dodava vo listata i potoa proveruva dali navistina e dodadena 
 * taka sto gi proagja site stavki od listata so studiski programi
*/
public class Test4_AddNewStudyProgramCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testAddNewStudyProgram() throws Exception {
		String studyProgramName = "SKIT";
		
		driver.get("http://localhost:3000/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add Student'])[1]/following::button[1]")).click();
	    driver.findElement(By.name("studyProgramName")).click();
	    driver.findElement(By.name("studyProgramName")).clear();
	    driver.findElement(By.name("studyProgramName")).sendKeys(studyProgramName);
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add Study Program'])[1]/following::button[1]")).click();
	    Thread.sleep(5000);
	    
	    List<WebElement> elements = driver.findElement(By.id("studyProgramsListCard")).findElements(By.tagName("li"));
	    List<WebElement> itemNames = driver.findElements(By.className("studyProgramItemNames"));
	    boolean studyProgramAdded = false;
	    
	    for(int i=0; i<elements.size(); i++) {
	    	if(itemNames.get(i).getText().equals(studyProgramName)) {
	    		studyProgramAdded = true;
	    		break;
	    	}
	    }
	    
	    Assert.assertTrue(studyProgramAdded);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}


}

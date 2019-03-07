package studentSchedulerSeleniumTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/*
 * Test za dodavanje na nov student. Dokolku ima 2 ili povekje studenti so isto ime i prezime go sporeduva po indeks
 * so cel da proveri dali navistina e dodaden vistinskiot student vo listata so studenti(baza)
*/
public class Test3_AddNewStudentCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testAddNewStudent() throws Exception {
		String index = "623456";
		String name = "David";
		String sName = "Davidovski";
		String studyProgram = "PET";
		
		driver.get("http://localhost:3000/");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Student Scheduler'])[1]/following::button[1]")).click();
	    driver.findElement(By.name("studentIndex")).click();
	    driver.findElement(By.name("studentIndex")).clear();
	    driver.findElement(By.name("studentIndex")).sendKeys(index);
	    driver.findElement(By.name("studentIme")).click();
	    driver.findElement(By.name("studentIme")).clear();
	    driver.findElement(By.name("studentIme")).sendKeys(name);
	    driver.findElement(By.name("studentLastName")).click();
	    driver.findElement(By.name("studentLastName")).clear();
	    driver.findElement(By.name("studentLastName")).sendKeys(sName);
	    driver.findElement(By.name("studyProgramsSelect")).click();
	    new Select(driver.findElement(By.name("studyProgramsSelect"))).selectByVisibleText(studyProgram);
	    driver.findElement(By.name("studyProgramsSelect")).click();
	    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Add Study Program'])[1]/following::button[1]")).click();
	    Thread.sleep(3000);
	    
	    
		List<WebElement> elements = driver.findElement(By.id("studentsListCard")).findElements(By.tagName("li"));
		List<WebElement> ItemNames = driver.findElements(By.className("studentItemNames"));
		List<WebElement> ItemLastNames = driver.findElements(By.className("studentItemLastNames"));
		boolean studentAdded = false;
		
		for(int i=0; i<elements.size(); i++) {
			if(ItemNames.get(i).getText().equals(name) && ItemLastNames.get(i).getText().equals(sName)) {
				elements.get(i).click();
				Thread.sleep(3000);
				WebElement indexStudentDetails = driver.findElements(By.className("studentDetailsIndexes")).get(0);
				WebElement studyProgramStudentDetails = driver.findElements(By.className("studentDetailsStudyProgramsNames")).get(0);
				
				if(indexStudentDetails.getText().equals(index) && studyProgramStudentDetails.getText().equals(studyProgram)) {
					studentAdded = true;
					break;
				}
					
				elements.get(i).findElements(By.className("row")).get(0).click();
				Thread.sleep(1000);
			}
		}
		
		Assert.assertTrue(studentAdded);
	}
		    
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

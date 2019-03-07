package studentSchedulerSeleniumTestCases;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/*
 * Test za editiranje na prviot student od listata so studenti. Najprvo gi menuva site atributi osven indeksot(klucot)
 * i posle go naogja studentot so isti indeks i za nego proveruva dali se smeneti atributite po klik na kopceto submit
*/
public class Test5_EditStudentCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void testEditStudent() throws Exception {
		driver.get("http://localhost:3000/");
		Thread.sleep(3000);
		
		WebElement firstStudentItem = driver.findElement(By.id("studentsListCard")).findElements(By.tagName("li")).get(0);
		firstStudentItem.click();
		Thread.sleep(2000);
		
		WebElement btnEdit = firstStudentItem.findElements(By.className("btn-edit")).get(0);
		btnEdit.click();
		Thread.sleep(2000);
		
		String name = "Aleksandar";
		String sName = "Veskovski";
		String studyProgram = driver.findElement(By.id("studyProgramsListCard")).findElements(By.className("studyProgramItemNames")).get(0).getText();
		
		WebElement nameElement = driver.findElement(By.name("studentIme"));
		nameElement.clear();
		nameElement.sendKeys(name);
		
		WebElement sNameElement = driver.findElement(By.name("studentPrezime"));
		sNameElement.clear();
		sNameElement.sendKeys(sName);
		
		WebElement studyProgramElement = driver.findElement(By.name("studentNasoka"));
		studyProgramElement.click();
	    new Select(studyProgramElement).selectByVisibleText(studyProgram);
	    studyProgramElement.click();
	    
	    WebElement btnSubmit = firstStudentItem.findElements(By.className("btn-submit")).get(0);
	    btnSubmit.click();
		Thread.sleep(3000);
	    
		firstStudentItem.click();
		String studentNameUpdated = driver.findElement(By.id("studentsListCard")).findElements(By.className("studentItemNames")).get(0).getText();
		String studentSurnameUpdated = driver.findElement(By.id("studentsListCard")).findElements(By.className("studentItemLastNames")).get(0).getText();
		String studyProgramUpdatedName = driver.findElement(By.id("studentsListCard")).findElements(By.className("studentDetailsStudyProgramsNames")).get(0).getText();
		
		boolean isStudentUpdated = true;
		
		if(!name.equals(studentNameUpdated) || !sName.equals(studentSurnameUpdated) || !studyProgram.equals(studyProgramUpdatedName))
			isStudentUpdated = false;
		
		Assert.assertTrue(isStudentUpdated);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

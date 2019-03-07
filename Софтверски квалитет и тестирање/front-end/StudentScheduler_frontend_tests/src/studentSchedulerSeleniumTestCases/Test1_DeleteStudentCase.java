package studentSchedulerSeleniumTestCases;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * Go brise prviot student od lsitata so studenti i potoa proveruva dali toj student seuste se naogja vo listata
 */
public class Test1_DeleteStudentCase {
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Moni\\Downloads\\DIME\\FAX\\geckodriver-eclipse-selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void deleteStudentTest() throws Exception {
		driver.get("http://localhost:3000/");
		Thread.sleep(3000);
		
		WebElement firstStudentItem = driver.findElement(By.id("studentsListCard")).findElements(By.tagName("li")).get(0);
		firstStudentItem.click();		
		String firstStudentItemIndex = driver.findElement(By.id("studentsListCard")).findElements(By.className("studentDetailsIndexes")).get(0).getText();
		
		WebElement btnDelete = firstStudentItem.findElements(By.className("btn-delete")).get(0);
		btnDelete.click();
		Thread.sleep(3000);
		
		boolean isStudentDeleted = true;
		List<WebElement> students = driver.findElement(By.id("studentsListCard")).findElements(By.tagName("li"));
		for(int i=0; i<students.size(); i++) {
			students.get(i).click();
			Thread.sleep(3000);
			
			WebElement indexStudentDetails = driver.findElements(By.className("studentDetailsIndexes")).get(0);
			if(indexStudentDetails.getText().equals(firstStudentItemIndex)) {
				isStudentDeleted = false;
				break;
			}
			
			students.get(i).findElements(By.className("row")).get(0).click();
			Thread.sleep(1000);
		}
		
		Assert.assertTrue(isStudentDeleted);
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
}

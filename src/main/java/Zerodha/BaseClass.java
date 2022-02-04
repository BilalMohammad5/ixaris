package Zerodha;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {
	public WebDriver driver;
	public static Logger log = LogManager.getLogger(BaseClass.class.getName());

	// fetching test par ameters from mysql database

	public WebDriver initDriver() throws IOException, FileNotFoundException, ClassNotFoundException, SQLException {

		// fetching test parameters from DB
		//System.out.println("call made to fetch test parameters from DB");
		//SqlDbConnect.getDb();

		Properties prop = new Properties();

		FileInputStream FIS = new FileInputStream(
				"E:\\automation\\Kite\\src\\main\\java\\properties\\output.properties");

		prop.load(FIS);

		// fetching browser and url from Map
		// Get a set of the entries

		String browser = prop.getProperty("Browser_Name");
		String url = prop.getProperty("url");
		if (browser.equals("chrome")) {
			// System.out.println("Test initiated using chrome browser");
			log.info("Test initiated using chrome browser");
			System.setProperty("webdriver.chrome.driver", "E:\\automation\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			// System.out.println(url);
			driver.get(url);
			log.info(driver.getTitle());

		}

		if (browser.equals("IE")) {
			System.out.println("Test initiated using IE  browser");
			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new EdgeDriver();
			driver.get(url);
		}

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	public void screenshot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver; // taking screenshot with help of driver
		File source = ts.getScreenshotAs(OutputType.FILE); // saving screenshot to a File
		String destinationFile = System.getProperty("user.dir") + "\\Reports\\" + testCaseName + ".png"; //creating aPNG filewith dynamic methodname and webdriverinstance

		FileUtils.copyFile(source, new File(destinationFile)); // Transferring PNG file from source to Destination
																// location in project folder
		System.out.println("Ereor screenshot attached with " + testCaseName);
	}

}
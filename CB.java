package message;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;



@SuppressWarnings("unused")
public class CB {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String csv_file = "C:\\Users\\Developer\\Documents\\pega\\search_string.csv";
		//String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\data_scientist_25sep_1.csv";
		String baseurl = "http://hiring.careerbuilder.com";
	   // driver = new FirefoxDriver();
		Actions actions = new Actions(driver);
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
	/*	driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		Thread.sleep(20000);*/
		driver.findElement(By.xpath("//*[@id='top-bar']/nav[1]/div/div/ul/li[2]/a/span")).click();
		driver.findElement(By.xpath("//*[@id='cbsys_login_email']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='cbsys_login_password']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='btnsigninemp']")).click();
		driver.findElement(By.xpath("//*[@id='_ctl4_RDBAdvancedSearch']")).click();
		driver.findElement(By.xpath("//*[@id='txtKeywords']")).sendKeys("\"Pega Architect\"");
		driver.findElement(By.xpath("//*[@id='experiencelow']")).sendKeys("10");
		driver.findElement(By.xpath("//*[@id='btnSearch2']")).click();
		List<WebElement> divs = driver.findElements(By.xpath("//*[@id='searchresults']/ul[1]/li"));
		int rec_num = divs.size();
		System.out.println(rec_num);
	     // List l1 = new ArrayList();
	      for(int i=1;i<=rec_num;i++)
	      {	
	    	  String xpath = "//*[@id='searchresults']/ul[1]/li[" + i + "]/div/div[1]/span[2]/a";
	    	  driver.findElement(By.xpath(xpath)).click();
	    	  driver.findElement(By.xpath("//*[@id='forward']")).click();
	    	  driver.findElement(By.xpath("//*[@id='_FastFlip_tbToAddressesXavierTest']")).sendKeys("fa4b1d296231-recruiter@app.breezyhr.com");
	    	  driver.findElement(By.xpath("//*[@id='btnFFForwardCandidate']")).click();
	    	  driver.findElement(By.xpath("//*[@id='resumeFlipLnkBack']/a")).click();
	    	  driver.findElement(By.xpath("//*[@id='HTMLTag']")).click();
	    	
	      }
	      //*[@id="HTMLTag"]/body/div[23]/div[3]/div/button
	      
		
	}}

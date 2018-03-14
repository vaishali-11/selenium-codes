package message;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.Keys;


@SuppressWarnings("unused")
public class Breezy_upload {
	
	
	public static void main(String[] args) throws IOException, InterruptedException, AWTException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		   
		WebDriver driver = new ChromeDriver();
		String baseurl = "https://breezy.hr/";		//BASE URL
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get(baseurl);
		
		//SIGNIN
		
		driver.findElement(By.xpath("/html/body/article/nav[2]/div/dl/dd[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("/html/body/article/section[1]/div/div/div/div[2]/form/div[3]/input")).sendKeys("suresh@mlwiz.com");
		driver.findElement(By.xpath("/html/body/article/section[1]/div/div/div/div[2]/form/div[4]/input")).sendKeys("monk1234");
		driver.findElement(By.xpath("/html/body/article/section[1]/div/div/div/div[2]/form/div[5]/button")).click();
		
		String path = "C:\\Users\\Developer\\Desktop\\resume_all\\resume_2017_04\\2017\\12\\";
		//File folder = new File(path);
		//File[] listOfFiles = folder.listFiles();
		String outpath = "C:\\Users\\Developer\\Desktop\\resume_all\\resume_2017_04\\filenames.csv";
		String inpath = "C:\\Users\\Developer\\Desktop\\resume_all\\resume_2017_04\\resume.csv";
		/*for (int i = 0; i < listOfFiles.length; i++) {
		if (listOfFiles[i].isFile()) {
		String file = listOfFiles[i].getName();
		String f_name = path + file;*/
		
		//CANDIDATES
		CSVReader reader = new CSVReader(new FileReader(inpath));
			String[] cell;
			
			//Reading through out the file until the last record
			
	while((cell=reader.readNext())!=null){

	    String filenames = cell[0];
	    String f_name = path + filenames;
		System.out.println(f_name);
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[1]/div/div/div[3]/ul[1]/li[1]/span/a/i")).click();
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[1]/div/div/div[3]/ul[1]/li[3]/a/span")).click();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[1]/div/div/div[3]/ul[1]/li[3]/a")).click();
		//ADD CANDIDATE
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[2]/div/article/div/header[2]/div[2]/a[7]/span")).click();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[2]/div/article/div/header[2]/div[2]/a[7]")).click();
		
		
		//IMPORT RESUME OPTION SELECTION AND SENDING RESUME PATH
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[4]/div[2]/div[2]/div[1]/div/input")).sendKeys(f_name);
		
		//POOL SELECTION
		
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/button/span[1]")).click();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/input[1]")).sendKeys(" Sr. Oracle DBA");
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/input[1]")).sendKeys(Keys.ENTER);
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li/div[9]/a/div/div/h3/span")).click();
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li/div[3]/a/div/div/h3/span")).click();
		//driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li/div[4]/a/div/div/h3")).click();
		//ADD CANDIDATE COMPLETE
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[2]/div[4]/div/div/div/button/span")).click();
		//*[@id="BaseApp"]/body/div[7]/div/div/div[2]/div[1]/div[2]/div[1]/div/ul/li/div[3]/a/div/div/h3/span
		//Checking upload sucessfull or not
		
		try{
			
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[4]/div[2]/div[1]/div/div/span")).isDisplayed();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[7]/div/div/div[2]/div[1]/div[4]/div[2]/div[4]/form/div[3]/ul/li[1]/a")).click();
		System.out.println("DUPLICATE");
		String[] filename = {filenames,"Duplicate"};
		//String[] filename = {listOfFiles[i].getName(),"Duplicate"};
		CSVWriter w = new CSVWriter(new FileWriter(outpath,true));
        w.writeNext(filename);
        w.close(); 
		}
		
		catch(NoSuchElementException e){
			System.out.println("uploaded");
			//String[] filename = {listOfFiles[i].getName(),"Uploaded"};
			String[] filename = {filenames,"Uploaded"};
			CSVWriter w = new CSVWriter(new FileWriter(outpath,true));
	        w.writeNext(filename);
	        w.close();
					
			}
			
		
		Thread.sleep(2000);
		}
		reader.close();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[1]/div/div/div[3]/ul[2]/li[7]/span/a/span")).click();
		driver.findElement(By.xpath("//*[@id='BaseApp']/body/div[2]/div/div[1]/div/div/div[3]/ul[2]/li[7]/span/ul/li[8]/a")).click();
		driver.close();
	}}
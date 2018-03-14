package message;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("unused")
public class job_diva_test{
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\JARS\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String csv_file = "C:\\Users\\Developer\\Desktop\\monster_data\\jobdiva_names.csv";
		String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\jobdiva_op1.csv";
		String baseurl = "https://www.jobdiva.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		driver.findElement(By.id("login_menu")).click();
		driver.findElement(By.id("login")).sendKeys("vaishali@dsla1.com");
		driver.findElement(By.id("pass")).sendKeys("monk1234");
		
		CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
        int i = 0;
		driver.findElement(By.xpath("//*[@id='login_dropdown']/div[2]/form/div[3]/button")).click();
		while((cell=reader.readNext())!=null){
			
	         String input = cell[i];
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).clear();
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).sendKeys(input);
			driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/a")).click();
		
		boolean b;
	    try {
	      driver.findElement(By.xpath("/html/body/div[10]"));
	    	 	b=true;
	    } catch (NoSuchElementException e) {
	        b = false;
	     
	    }
		
		if(b)
		{
			System.out.println("NOT FOUND MESSAGE DISPLAYED");
				
			
		}
		
		else
		{
		System.out.println("**********RECORD FOUND************");
			
		}	
		
		}
		
	    driver.findElement(By.xpath("//*[@id='jdHeader']/div[2]/img")).click();
	    driver.close();
	   reader.close();
	}

	}
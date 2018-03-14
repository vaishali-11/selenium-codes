package message;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

//import com.gargoylesoftware.htmlunit.javascript.host.Set;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("unused")
public class jobdiva_name {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String baseurl = "https://www.jobdiva.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get(baseurl);
		Thread.sleep(7000);
		//driver.findElement(By.id("btn_navbar_toggle")).click();
		driver.findElement(By.id("login_menu")).click();
		Thread.sleep(5000);
		driver.findElement(By.id("login")).sendKeys("vaishali@dsla1.com");
		driver.findElement(By.id("pass")).sendKeys("monk1234");
		driver.findElement(By.xpath("//*[@id='login_dropdown']/div[2]/form/div[3]/button")).click();
		Thread.sleep(30000);
		String[] file;
		int j =0;
		int j1=1;
		String stringfile = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/search_strings1.csv";
		CSVReader reader1 = new CSVReader(new FileReader(stringfile));
		
		File  f = new File("C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD1/");
		System.out.println(f.mkdir());
		
		while((file=reader1.readNext())!=null)
		{
			String filename=file[j];
			//int val = Integer.parseInt(file[j1]);
		
		String csv_file = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD/monster_skill_" + filename + ".csv";
		String outpath = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD1/monster_skill_" + filename + ".csv";
		CsvListReader reader_col = new CsvListReader(new FileReader(csv_file), CsvPreference.STANDARD_PREFERENCE);
		
		List<String> columns;  
		String[] cell;
		int i = 1;
       int search_skill = 0;
   	   System.out.println("matching for file " + filename);
   	   
   	myloop:	while((columns=reader_col.read())!=null){

 			 System.out.println(search_skill);
			   
			   int len = columns.size();			   
	         
			   String input = columns.get(len-1);
	         driver.navigate().refresh();
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).clear();
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).sendKeys(input);
			 driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/a")).click();
			
		
		boolean b;
	    try {
	  
	    	driver.findElement(By.id("candidate"));
	    	b=true;
	    } catch (NoSuchElementException e) {
	        b = false;
	     
	    }
		
		if(b)
		{
			
			String num = driver.findElement(By.id("candidate")).getText();
			String[] count = {input, num};
			columns.add(num);
			CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
		    writer.write(columns);
		    writer.close();
		
			
		}
		
		else
		{String num = "0";
		
		
		String[] count = {input, num};
		columns.add(num);
		CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
	    writer.write(columns);
	    writer.close();

			
		}	
		search_skill++;
		}
		 reader_col.close();
		}
		reader1.close();
	    driver.findElement(By.xpath("//*[@id='jdHeader']/div[2]/img")).click();
	    driver.close();
	   
	}

	}
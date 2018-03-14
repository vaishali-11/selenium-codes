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
public class jobdiva {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\JARS\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String csv_file = "C:\\Users\\Developer\\Desktop\\monster_data\\jobdiva_names.csv";
		String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\jobdiva_op.csv";
		String baseurl = "https://www.jobdiva.com/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		driver.findElement(By.id("login_menu")).click();
		driver.findElement(By.id("login")).sendKeys("vaishali@dsla1.com");
		driver.findElement(By.id("pass")).sendKeys("monk1234");
		
		//CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
        int i = 0;
		driver.findElement(By.xpath("//*[@id='login_dropdown']/div[2]/form/div[3]/button")).click();
		//while((cell=reader.readNext())!=null){
			
	         //String input = cell[i];
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).clear();
	         driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/input")).sendKeys("Vinod Kumar ");
			driver.findElement(By.xpath("//*[@id='jdHeader']/div[3]/a")).click();
		
		boolean b;
	    try {
	    	
	    	driver.findElement(By.id("all"));
	    	b = true;
	    } catch (NoSuchElementException e) {
	        b = false;
	     
	    }
		
		if(b)
		{
			String num = driver.findElement(By.xpath("//*[@id='all']/div")).getText();
			//String[] count = {"vinod kumar", num};
			List<WebElement> data = driver.findElements(By.xpath("/html/body/div[4]/div[2]/div/div"));
			int size = data.size();
			System.out.println(size);
			for(int j=1;j<=size;j++)
			{    String xpath = "/html/body/div[4]/div[2]/div/div[" + j + "]/div[1]/div[3]/a";
			     boolean b1;
			     try
			     {driver.findElement(By.xpath(xpath));
			     b1=true;} 
			     catch (NoSuchElementException e) {
				        b1 = false;
			     }
	             if(b1)
	             {driver.findElement(By.xpath(xpath)).click();
	             String mainWindowHandler = driver.getWindowHandle();
	             
	             Set<String> allWindowHandlers = driver.getWindowHandles();
	             
	             for (String currWindowHandler : allWindowHandlers) {
	              if (!currWindowHandler.equals(mainWindowHandler)) {
	             
	            	  driver.switchTo().window(currWindowHandler);
	               String currWindowTitle = driver.getTitle();
	               System.out.println("newtab");
		             String city = driver.findElement(By.xpath("//*[@id='cityLabel']")).getText();
		             System.out.println(city);
		             String[] count = {"vinod kumar", num,city};
		             CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
		             writer.writeNext(count);
		             writer.close();	
	               driver.close();
	               System.out.println("Quit new window, browser title is : " + currWindowTitle);    
	               }
	              } 
	             driver.switchTo().window(mainWindowHandler);}
	             else { System.out.println("notes");}
	             /*String oldTab = driver.getWindowHandle();
	             ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
	             if(driver.getWindowHandle().equals(oldTab)){
	             newTab.remove(oldTab);
	             driver.getWindowHandle()
	             // change focus to new tab
	             driver.switchTo().window(newTab.get(0));
	             System.out.println("newtab");
	             String city = driver.findElement(By.xpath("//*[@id='cityLabel']")).getText();
	             System.out.println(city);
	             // Do what you want here, you are in the new tab

	             driver.close();
	             // change focus back to old tab
	             driver.switchTo().window(oldTab);*/
	             
	             }}
	             
			
			   
				
			
		
		
		
			
		//}
		
	    driver.findElement(By.xpath("//*[@id='jdHeader']/div[2]/img")).click();
	    driver.close();
	   // reader.close();
	}}
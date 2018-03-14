package message;
import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Repeatmessage {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		WebDriver driver;
		//String link;

		String baseurl = "https://secure.meetup.com/login/";
		String CSV_file = "C:\\Users\\Developer\\Desktop\\Bootcamp_msg\\new_list1.csv";
		String outpath = "C:\\Users\\Developer\\Desktop\\Bootcamp_msg\\final_message_output1.csv";
		String name = "spn2113@columbia.edu";
		String password = "monk1234";				
		
		//Creating web browser object
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		
		//Entering login details
		
		driver.findElement(By.id("email")).sendKeys(name);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.name("submitButton")).click();
		
		//Navigating through the profile Menu
		
		driver.findElement(By.id("profileNav")).click();
		driver.navigate().to("https://www.meetup.com/Central-NJ-Data-Science-Meetup/");
		driver.navigate().to("https://www.meetup.com/Central-NJ-Data-Science-Meetup/members/");
      
              
      
		//Reading the data from CSV file
		@SuppressWarnings("resource")
			CSVReader reader = new CSVReader(new FileReader(CSV_file));
   			String[] cell;
	        int count = 1;
   			//Reading through out the file until the last record
   			
   			while((cell=reader.readNext())!=null){
myloop:		for(int i=0;i<1;i++){
		    String profile_link = cell[i];
		    String[] status = { "FAIL", profile_link };

		  
			String subject = cell[i+1];
		    
		    driver.navigate().to("https://www.meetup.com/Central-NJ-Data-Science-Meetup/members/" + profile_link);
		    
		    //Checking whether profile exists
 
	     	boolean b;
		    try {
		    	driver.findElement(By.linkText("Message"));
		    	b = true;
		    } catch (NoSuchElementException e) {
		        b = false;
		     
		    }
		    if(b){
		  
		    driver.findElement(By.linkText("Message")).click();
		  
		    //Writing message in the message box
		  		  
		    driver.findElement(By.id("messaging-new-convo")).sendKeys(subject);
		
		    WebElement ele=driver.findElement(By.id("messaging-new-send"));
		   
		    Thread.sleep(25000);  //30 secs waiting time before sending message
		   
		      ele.click();
		      Thread.sleep(5000); //5 secs waiting time to allow the page after sending message to load
		    }
		    else{
		    	System.out.println(profile_link + "," + status);
		    	
		    	//To output if profile does	not exits
		        
				CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
		        writer.writeNext(status);
		        writer.close();
		    	break myloop;
		    }
		 
		    
		   	} //FOR loop ends
			
		    //Returning back to main page to send more messages
		
   		   if(count <= 4){
   			   System.out.println("Count = " + count);
   		   count= count + 1;
   			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
   		    driver.navigate().to("https://www.meetup.com/Central-NJ-Data-Science-Meetup/members/");
   		   } 
   		   else{
   			   Thread.sleep(60000);
   			   count = 1;
   		   }
			
			} //While loop ends
   			
   			driver.findElement(By.id("profileNav")).click();	
	        driver.navigate().to("https://www.meetup.com/logout/");
	       
	}

	
}


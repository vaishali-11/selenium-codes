package message;

import org.openqa.selenium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("unused")
public class monster1 {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		//System.setProperty("webdriver.gecko.driver", "D:\\Downloads_new\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		WebDriver driver;
		//String link;
		String outpath = "C:\\Users\\Developer\\Desktop\\monster_data.csv";
		String baseurl = "https://hiring.monster.com";
		String userid = "xmlwizx02";
		String password= "monk1234";
		driver = new FirefoxDriver();
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		Thread.sleep(15000);
		/*driver.findElement(By.className("form-control")).getAttribute("title")).sendKeys(userid);
		driver.findElement(By.partialLinkText("Password")).sendKeys(password);
		driver.findElement(By.linkText("Sign In")).click();*/	
	    driver.findElement(By.id("wtvN_Candidates")).click();
     	driver.findElement(By.id("wtvS_SearchRes")).click();
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_llLocations2_input0")).sendKeys("New York, NY");
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_tblr_facetSkills2_input0")).sendKeys("Data Scientist");
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_tblr_facetSkills2_input1")).sendKeys("python");
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_SearchBtnBottom")).click();
     	
     	WebElement table_element = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_ctl00']"));
     	List<WebElement> tr_collection=driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_ctl00']/tbody/tr"));
     	List<WebElement> name = table_element.findElements(By.xpath("//div[@class='SFName']"));
     	List<WebElement> location = table_element.findElements(By.xpath("//div[@class='SFLocation']"));
     	List<WebElement> skillmatch = table_element.findElements(By.xpath("//div[@class='skillsList hideWhenBlocked']"));
     	CSVWriter writer = new CSVWriter(new FileWriter(outpath));
     
      	for(WebElement element : name) {
     		
     		String[] name1 = {element.getText(),""};
            System.out.println(name1);
           // CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(name1);}
	        //writer.close(); 
      	
      	for(WebElement element1 : location){
     		
     		String[] location1 = {"",element1.getText()};
            System.out.println(element1.getText());
          //  CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.flush();
            writer.writeNext(location1);}
	      //  writer.close();

      		for(WebElement element2 : skillmatch){
		
      				String[] skill = {"","",element2.getText()};
      				//ring name1 = element.getText();
      			///	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
      		        writer.flush();
      				writer.writeNext(skill);
      		      //  writer.close();
					System.out.println(element2.getText());
      			}
      	
     /* 		CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(name1);*/
	        writer.close();
      	
      		driver.findElement(By.id("ctl00_ctl00_cphHeader_navHeader_loginBar_lnkLogout")).click();
      		driver.close();
      /*or(WebElement element : name) {
		
		String name1 = element.getText();
		System.out.println(element.getText());
      	}*/
     	
     	//String attr = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_ctl00']")).getAttribute("");
     	//String text = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_ctl00']")).getText();
     	//System.out.println(text);
     	     	
     	
     	
     	
     	
	}}
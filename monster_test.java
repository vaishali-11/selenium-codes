package message;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class monster_test {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		WebDriver driver;
		String csv_file = "C:\\Users\\Developer\\Desktop\\monster_data\\search_strings.csv";
		//String outpath = "C:\\Users\\Developer\\Desktop\\monster_datascientist1.csv";
		String baseurl = "https://hiring.monster.com";
	    driver = new FirefoxDriver();
		Actions actions = new Actions(driver);
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		Thread.sleep(20000);
			
		CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
        int count = 0;
			//Reading through out the file until the last record
			
		while((cell=reader.readNext())!=null){
	    int search_skill = 1;
		String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\monster_skill_" + search_skill + ".csv";
		String search_string = cell[count];
	    driver.findElement(By.id("wtvN_Candidates")).click();
	   
     	driver.findElement(By.id("wtvS_SearchRes")).click();
     	driver.findElement(By.id("standardResumeSearch")).click();
        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_keywords")).sendKeys(search_string);
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_bottomSearchBtn")).click();
     	
     	WebElement table_element = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']"));
     	List<WebElement> tr_collection=driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr"));
        int size = tr_collection.size();
        boolean b;
	    try {
	    	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerLast"));
	    	b = true;
	    } catch (NoSuchElementException e) {
	        b = false;
	     
	    }
        //boolean page_field = driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_currPage")).isDisplayed();
        if (b){
        	boolean b1;
    	    try {
    	    	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerLast"));
    	    	b1 = true;
    	    } catch (NoSuchElementException e) {
    	        b1 = false;
    	     
    	    }
    	    if (b1){
    	   String p =	driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div")).getText();
    	  
    	   int page = 1;
     	while(page <= Integer.parseInt(p))
     	{
        for(int i = 1 ; i<=size ; i++)
        { 
        	String n = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[1]/label/span";
        	String loc = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[1]";
        	String expjob = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[2]";
        	String cand_brief = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[3]/div";
        	String resume_upd = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[1]/span[2]";
        	String highest_edu = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[3]/span[2]";
         	String target_job_title = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[4]/span[2]";
        	String des_status = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[5]/span[2]";
        	String desired_job_type ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[6]/span[2]";
        	String desired_salary = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[7]/span[2]";
        	String relocate ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[8]/span[2]";
        	String authorization ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[9]/span[2]";
        	String militarystatus ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[10]/span[2]";
        	String name = table_element.findElement(By.xpath(n)).getText();
        	String location=table_element.findElement(By.xpath(loc)).getText();
        	String exp_job_title = table_element.findElement(By.xpath(expjob)).getText();
         	String candidate_brief = table_element.findElement(By.xpath(cand_brief)).getText();
         	String resume_date = table_element.findElement(By.xpath(resume_upd)).getText();
         	String high_edu = table_element.findElement(By.xpath(highest_edu)).getText();
         	String job_title = table_element.findElement(By.xpath(target_job_title)).getText();
         	String status = table_element.findElement(By.xpath(des_status)).getText();
         	String job_type = table_element.findElement(By.xpath(desired_job_type)).getText();
         	String salary = table_element.findElement(By.xpath(desired_salary)).getText();
         	String relocaate = table_element.findElement(By.xpath(relocate)).getText();
         	String auth = table_element.findElement(By.xpath(authorization)).getText();
         	String mili_status = table_element.findElement(By.xpath(militarystatus)).getText();
         	
         	String[] info = {name,location,exp_job_title,candidate_brief,resume_date,high_edu,job_title,status,job_type,salary,relocaate,auth,mili_status};
         	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(info);
	        writer.close();
        	//System.out.println(name + "location: " + location + "  :====>" +  exp_job_title + "  :===> " + candidate_brief + "   " + resume_date + high_edu + job_title + status + job_type + salary + relocaate + auth + mili_status);
        	
        }
        page++;
        System.out.println(page);
        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_currPage")).clear();
        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_currPage")).sendKeys(String.valueOf(page));
        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerGoBtn")).click();
	}}}
    	    else
    	    	{
    	    	for(int i = 1 ; i<=size ; i++)
    	        { 
    	        	String n = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[1]/label/span";
    	        	String loc = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[1]";
    	        	String expjob = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[2]";
    	        	String cand_brief = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[3]/div[3]/div";
    	        	String resume_upd = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[1]/span[2]";
    	        	String highest_edu = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[3]/span[2]";
    	         	String target_job_title = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[4]/span[2]";
    	        	String des_status = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[5]/span[2]";
    	        	String desired_job_type ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[6]/span[2]";
    	        	String desired_salary = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[7]/span[2]";
    	        	String relocate ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[8]/span[2]";
    	        	String authorization ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[9]/span[2]";
    	        	String militarystatus ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[10]/span[2]";
    	        	String name = table_element.findElement(By.xpath(n)).getText();
    	        	String location=table_element.findElement(By.xpath(loc)).getText();
    	        	String exp_job_title = table_element.findElement(By.xpath(expjob)).getText();
    	         	String candidate_brief = table_element.findElement(By.xpath(cand_brief)).getText();
    	         	String resume_date = table_element.findElement(By.xpath(resume_upd)).getText();
    	         	String high_edu = table_element.findElement(By.xpath(highest_edu)).getText();
    	         	String job_title = table_element.findElement(By.xpath(target_job_title)).getText();
    	         	String status = table_element.findElement(By.xpath(des_status)).getText();
    	         	String job_type = table_element.findElement(By.xpath(desired_job_type)).getText();
    	         	String salary = table_element.findElement(By.xpath(desired_salary)).getText();
    	         	String relocaate = table_element.findElement(By.xpath(relocate)).getText();
    	         	String auth = table_element.findElement(By.xpath(authorization)).getText();
    	         	String mili_status = table_element.findElement(By.xpath(militarystatus)).getText();
    	         	
    	         	String[] info = {name,location,exp_job_title,candidate_brief,resume_date,high_edu,job_title,status,job_type,salary,relocaate,auth,mili_status};
    	         	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
    		        writer.writeNext(info);
    		        writer.close();
    	    	
    	    	}
    	    	}
     	search_skill++;
     	}
      		driver.findElement(By.id("ctl00_ctl00_cphHeader_navHeader_loginBar_lnkLogout")).click();
      		driver.close();
          	reader.close();
     	
     	
     	
	}}

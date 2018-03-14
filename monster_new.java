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
import java.util.Calendar;



@SuppressWarnings("unused")
public class monster_new {
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String csv_file = "C:\\Users\\Developer\\Documents\\pega\\search_string.csv";
		//String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\data_scientist_25sep_1.csv";
		String baseurl = "https://hiring.monster.com";
	   // driver = new FirefoxDriver();
		Actions actions = new Actions(driver);
		//driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
	/*	driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		Thread.sleep(20000);*/
		driver.findElement(By.xpath("//*[@id='mobile-navbar-search']/ul/li ")).click();
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[2]/div[1]/input")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[2]/div[2]/input")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[4]/button")).click();	
		File  f = new File("C:\\Users\\Developer\\Documents\\pega\\scrape\\");
		System.out.println(f.mkdir());
		CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
        int count = 0;
			//Reading through out the file until the last record
			
		while((cell=reader.readNext())!=null){
	    int search_skill = 1;
		String search_string = cell[count];
		Thread.sleep(10000);
		driver.findElement(By.id("top_menu_372")).click();;
		//Select canddown = new Select(driver.findElement(By.id("top_menu_372")));
		driver.findElement(By.id("top_menu_372_373")).click();
	   // driver.findElement(By.id("wtvN_Candidates")).click();
	    String outpath = "C:\\Users\\Developer\\Documents\\pega\\scrape\\monster_skill_" + search_string + ".csv";
	   // String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\monster_18oct\\scrape\\monster_skill_net_c#_MBS_SQL.csv";

	  //  driver.findElement(By.id("wtvS_SearchRes")).click();
	    Thread.sleep(5000);
     	driver.findElement(By.id("standardResumeSearch")).click();
        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_keywords")).sendKeys(search_string);
       
        Select dropdown1 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_minimumDateRange")));
        dropdown1.selectByVisibleText("Today");
        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_maximumDateRange")));
        dropdown2.selectByVisibleText("1 month");
      
     	driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_topSearchBtn")).click();
     	
     	WebElement table_element = driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']"));
     	List<WebElement> tr_collection=driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr"));
        int size = tr_collection.size();
        boolean b;
	    try {
	    	String style = driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerLast")).getAttribute("style");
	    	System.out.println(style);
	    	if((style.equals("display: block;"))){
	    	b = true;}else{b=false;}
	    } catch (ElementNotInteractableException e) {
	        b = false;
	     
	    }
        
          if (b){System.out.println("In first part_pages more than one or five");
          //int total_page;
        	  driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerLast")).click();
        	  List<WebElement> page_list = driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div"));
  	    	int page_count = page_list.size();
  	    	System.out.println("Number of div found in the last section: " + page_count);
  	    	Thread.sleep(7000);
  	    	String path = "//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div["+ page_count + "]";
  	        String p = driver.findElement(By.xpath(path)).getText();
        	//  String p =	driver.findElement(By.xpath("//*[@id='pages']/div[9]")).getText();
        	  System.out.println("Total number of pages found: "+p);
        	  Thread.sleep(7000);
        	  driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerFirst")).click();
        	  Thread.sleep(3000);
        	/*  if(search_string.equals("(DATA SCIENTIST)"))
        	  {  total_page = Integer.parseInt(p);}
        	  else {  total_page = 6;
        	  }*/
        	//  System.out.println("Number of  Pages to scrape are : " + total_page);
    	   int page = 1;
 myloop:   	while(page <= Integer.parseInt(p))
     	{	Thread.sleep(2000);
floop1:        for(int i = 1 ; i<=size ; i++)
        { 
        	Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
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
        	String relocate = "//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[8]/span[2]";
        	String authorization ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[9]/span[2]";
        	//String militarystatus ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[2]/div[4]/div[10]/span[2]";
        	String keyword ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[3]/div[1]/span[2]";
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
         	String keyword_status = table_element.findElement(By.xpath(keyword)).getText();
         	
         	if(keyword_status.equals("Low Keyword Match")){break myloop;}
         	else{System.out.println(keyword_status);}
         	
         	String[] info = {name,location,exp_job_title,resume_date,high_edu,job_title,status,job_type,salary,relocaate,auth,keyword_status,candidate_brief,sdf.format(cal.getTime())};
         	if (name.isEmpty()){break floop1;} else {}
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
	}}
    	    else
    	    	{
    	    	boolean b1;
    	    	try{
    	    		driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div"));
    	    		b1 =true;
    	    	}
    	    	catch(NoSuchElementException e){
    	    		b1=false;
    	        	    	}
    	    	
    	    	if(b1)
    	    	{System.out.println("In the else part| Less than or equals to 5 pages found");
    	    	List<WebElement> page_list = driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div"));
      	    	int page_count = page_list.size();
      	    	System.out.println(page_count);
      	    	String path = "//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div["+ page_count + "]";
      	        String pages = driver.findElement(By.xpath(path)).getText();
            	
            	  System.out.println(pages);
            	//  driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pagerFirst")).click();
    	    	int total_page=Integer.parseInt(pages) +2;
    	    	int page =1;
 myloop2:	   	while(page <= page_count)
    	    	{   
    	    		String single_page_num="//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_pagingHeader_pnlComplex']/div[2]/div[3]/div["+ page + "]";
    	    	    driver.findElement(By.xpath(single_page_num)).click();
 floop2:   	    	for(int i = 1 ; i<=size ; i++)
    	        { 
    	    		Calendar cal = Calendar.getInstance();
        	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
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
    	        	String keyword ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[3]/div[1]/span[2]";
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
    	         	String keyword_status = table_element.findElement(By.xpath(keyword)).getText();
    	         	if(keyword_status.equals("Low Keyword Match")){break myloop2;}
    	         	else{System.out.println(keyword_status);}
    	         	if (name.isEmpty()){break floop2;} else {}
    	         	String[] info = {name,location,exp_job_title,resume_date,high_edu,job_title,status,job_type,salary,relocaate,auth,keyword_status,candidate_brief,sdf.format(cal.getTime())};
    	         	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
    		        writer.writeNext(info);
    		        writer.close();
    		        
    	    	
    	    	}page = page + 2;
    	    	}}
    	    	
    	    	else
    	    	{
    	    		System.out.println("ONLY 1 PAGE");
myloop3: 	    	for(int i = 1 ; i<=size ; i++)
    	    	
    	        {  Calendar cal = Calendar.getInstance();
    	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
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
    	        	String keyword ="//*[@id=" + "'ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl01_ctl00']/tbody/tr[" + i + "]/td[3]/div[1]/span[2]";
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
    	         	String keyword_status = table_element.findElement(By.xpath(keyword)).getText();
    	         	if(keyword_status.equals("Low Keyword Match")){break myloop3;}
    	         	else{System.out.println(keyword_status);}
    	         	if (name.isEmpty()){break myloop3;} else {}
    	         	String[] info = {name,location,exp_job_title,resume_date,high_edu,job_title,status,job_type,salary,relocaate,auth,keyword_status,candidate_brief,sdf.format(cal.getTime())};
    	         	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
    		        writer.writeNext(info);
    		        writer.close();
    		        }}
     	//search_skill++;
     	}}
		driver.findElement(By.xpath("//*[@id='nonResponsiveTopMenu']/ul/li[5]/a")).click();
		//driver.findElement(By.xpath("//*[@id='settingsDetailsMenu']/li[7]/a")).click();
		driver.findElement(By.linkText("Sign Out")).click();//linktext("//*[@id='settingsDetailsMenu']/li[7]/a")).click();
		//driver.findElement(By.id("ctl00_ctl00_cphHeader_navHeader_loginBar_lnkLogout")).click();
      		driver.close();
          	reader.close();
     	
     	
     	
	}}

package message;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


@SuppressWarnings("unused")
public class monster_download {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Developer\\Downloads\\JARS\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		WebDriver driver;
		 FirefoxProfile profile = new FirefoxProfile();
		 String baseurl = "https://hiring.monster.com";
	        //Download setting
	       profile.setPreference("browser.download.folderList", 2);
	        profile.setPreference("browser.helperapps.neverAsk.saveToDisk","doc");
	        profile.setPreference("browser.helperapps.neverAsk.saveToDisk","docx");
	        profile.setPreference("browser.helperapps.neverAsk.saveToDisk","rtf");
	        profile.setPreference("browser.helperapps.neverAsk.saveToDisk","pdf");
	        profile.setPreference("browser.download.dir", "C:\\Users\\Developer\\Desktop\\monster_data\\monster_28oct\\resume\\");
	        driver = new FirefoxDriver(profile);
	        Actions actions = new Actions(driver);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
			driver.get(baseurl);
		/*	driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		
			Thread.sleep(30000);
			driver.findElement(By.id("wtvN_Candidates")).click();
			Thread.sleep(5000);
	     	driver.findElement(By.id("wtvS_SearchRes")).click();
	     	Thread.sleep(3000);
	     	driver.findElement(By.id("standardResumeSearch")).click();
	     	Select dropdown1 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_minimumDateRange")));
	        dropdown1.selectByVisibleText("Today");
	        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_maximumDateRange")));
	        dropdown2.selectByVisibleText("1 month");
	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_topSearchBtn")).click();
	        String[] file;
			int j =0;
			String dfile="C:\\Users\\Developer\\Desktop\\monster_data\\monster_28oct\\dfile.csv";
			String stringfile = "C:\\Users\\Developer\\Desktop\\monster_data\\monster_28oct\\search_string1.csv";
			CSVReader reader1 = new CSVReader(new FileReader(stringfile));
			while((file=reader1.readNext())!=null)
			{   
				System.out.println("File change Occured||wait for 10 sec");
				Thread.sleep(10000);
				String filename=file[j];
				int val = Integer.parseInt(file[1]);
				int dcount = Integer.parseInt(file[3]);
				System.out.println("downloading from " + filename + ".csv");
			String csv_file = "C:\\Users\\Developer\\Desktop\\monster_data\\monster_28oct\\scrape\\monster_skill_" + filename + ".csv";
			String outpath = "C:\\Users\\Developer\\Desktop\\monster_data\\monster_28oct\\scrape\\monster_skill_" + filename + ".csv";
	        
			//System.out.println("Downloading from  file download_" + csv_file);
		CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
        int count = 0;
        int download=0;
		int match = 17;	
		//int key = 13;
		int search_skill = 0;
myloop1:   while((cell=reader.readNext())!=null){
	    	
	    	 
	    	 if(search_skill==val){System.out.println("FILE CHANGE");
	    	 CSVWriter writer = new CSVWriter(new FileWriter(dfile,true));
	 		String[] dvalue={filename,"EOF: val: ", String.valueOf(val),String.valueOf(download)};
	         writer.writeNext(dvalue);
	         writer.close();
	         break myloop1;}
			    else{System.out.println("No file change " + search_skill);}
myloop:	    for(count=0; count<1 ; count++)
		{
	
	       String jd = cell[match];
	        if(jd.equals("0")) { System.out.println("JobDiva match Count: " + jd);} 
	        else { System.out.println("More than 0 found ||MOving onto next candidate "); 
	        String[] info = {cell[count],"", "Already exists in JD",String.valueOf(download)};
	        CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(info);
	        writer.close();
	        break myloop;}
	        
		if(download==dcount){System.out.println("Completed " + dcount + " download  for the above search string|| move to next"); 
		CSVWriter writer = new CSVWriter(new FileWriter(dfile,true));
		int rleft = val-search_skill;
		String[] dvalue={filename,"Target Download completed: Records left for download: ",String.valueOf(rleft),String.valueOf(download)};
        writer.writeNext(dvalue);
        writer.close();break myloop1;}
		
		else{System.out.println("Still downloading from the same file||downloading count is " + download);}
		
		String name = cell[count]; 
		driver.navigate().refresh();
		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_controlResumeFilters_InputKeyword")).clear();
		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_controlResumeFilters_InputKeyword")).sendKeys(name);
		driver.findElement(By.id("btnFiltSearchBottom")).click();
        Thread.sleep(4000);
        String title=driver.findElement(By.id("linkResumeTitle")).getText();
        String view_label = driver.findElement(By.xpath("//*[@id='ViewedLabel']")).getText();
        if(!(view_label.equals("Viewed")))
        {
        if(name.equals(title))
        {driver.findElement(By.id("linkResumeTitle")).click();
       /* boolean error;
        try{ driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_resumeRow"));
        error = true;
        }
        catch(NoSuchElementException e)
        {if(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolder1_errorLabel")).isDisplayed())
        {break myloop;
        }error = false;
        
        }*/
      // if(error){
   /*     boolean b;
        	Thread.sleep(2000);
        	String display = driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_resumeRow")).getAttribute("style");
        	System.out.println(display);
        	if(!(display.equals("display: none;")))
        	{b=true;
        	}  
        	else {b=false;}    
        
       
        if(b)
        { 	System.out.println("Word download found? " + b);   
        	Thread.sleep(4000);
        		
		        driver.findElement(By.linkText("Word")).click();
		        download++;
		        String[] info = {name,title, "Word document",String.valueOf(download)};
		        System.out.println("Downloaded Word");
		        CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
		        writer.writeNext(info);
		        writer.close();
		  //      System.out.println(cell[key]);
		     
		     driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
		     Thread.sleep(5000);}
        else {
        	
        /*	boolean b1;
        	try{
        		driver.findElement(By.linkText("PDF"));
        		b1 = true;
        	}
        	catch(NoSuchElementException e)
        	{
        		b1= false;
        	}
        	
        	if(b1){
        		System.out.println("Downloading PDF");
        	driver.findElement(By.linkText("PDF")).click();
        	String[] info = {name,title, "PDF document"};
        	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(info);
	        writer.close();
	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
	        }
        	else{*/
     /*   		Thread.sleep(7000);
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_controlDetailTop_lblForward")).click();
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_BatchActions_forwardResumeMenuItem_ForwardResume1_txtbox_ForwardResume_Email")).clear();
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_BatchActions_forwardResumeMenuItem_ForwardResume1_txtbox_ForwardResume_Email")).sendKeys("vaishali@dsla1.com");
        		Thread.sleep(2000);
        		driver.findElement(By.linkText("Send")).click();
        		System.out.println("NO download Available|mailed");
        		download++;
        		String[] info = {name,title, "NO download Available|mailed",String.valueOf(download)};
            	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
            	System.out.println(cell[13]);
            	
    	        writer.writeNext(info);
    	        writer.close();
    	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
    	        Thread.sleep(5000);
        //	}
        }}
       //else{ System.out.println("Error message");}
      //  }
        else {
        	String[] info = {name,title, "Exact match not found|Skipped download",String.valueOf(download)};
        	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(info);
	        writer.close();
	        Thread.sleep(5000);
        	break myloop;}
        }
        else {String[] info = {name,title, "Already viewed|downloaded",String.valueOf(download)};
    	CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
        writer.writeNext(info);
        writer.close();Thread.sleep(5000);
    	break myloop;}}
	   search_skill++;}reader.close();}
        driver.findElement(By.id("ctl00_ctl00_cphHeader_navHeader_loginBar_lnkLogout")).click();
  		driver.close();
       reader1.close();*/
       
	}}
     	
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


@SuppressWarnings("unused")
public class Download {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		   //Download setting
		String downloadFilepath = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/resume_27jan/";
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		WebDriver driver = new ChromeDriver(cap);
		String baseurl = "https://hiring.monster.com";
	    driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		driver.findElement(By.xpath("//*[@id='mobile-navbar-search']/ul/li ")).click();
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[2]/div[1]/input")).sendKeys("prachi@mlwiz.com");
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[2]/div[2]/input")).sendKeys("Aiwiz317");
		driver.findElement(By.xpath("//*[@id='logInContainer']/div[1]/div[2]/div/div/div[4]/button")).click();
		File  cd = new File("C:/Users/Developer/Documents/monster_resume_2nd/25jan/CD1/");
		System.out.println(cd.mkdir());
		File  f1 = new File("C:/Users/Developer/Documents/monster_resume_2nd/25jan/resume_27jan/");
		System.out.println(f1.mkdir());
		File  f2 = new File("C:/Users/Developer/Documents/monster_resume_2nd/25jan/txt_res_27/");
		System.out.println(f2.mkdir());
			Thread.sleep(15000);
			driver.findElement(By.id("top_menu_372")).click();;
		
			driver.findElement(By.id("top_menu_372_373")).click();
		
	     	driver.findElement(By.id("standardResumeSearch")).click();
	     	Select dropdown1 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_minimumDateRange")));
	        dropdown1.selectByVisibleText("Today");
	        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_maximumDateRange")));
	        dropdown2.selectByVisibleText("1 month");
	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_topSearchBtn")).click();
	        String[] file;
			int j =0;
			String dfile="C:/Users/Developer/Documents/monster_resume_2nd/25jan/dfile_27jan.csv";
			String stringfile = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/search_strings1.csv";
			CSVReader reader1 = new CSVReader(new FileReader(stringfile));
			while((file=reader1.readNext())!=null)
			{   
				
			String filename=file[j];
			//	int val = Integer.parseInt(file[1]);
				int dcount = Integer.parseInt(file[1]);
				System.out.println("downloading from " + filename + ".csv");
			String csv_file = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD1/monster_skill_" + filename + ".csv";
			String outpath = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/CD1/monster_skill_" + filename + ".csv";
			
			
			
			CsvListReader reader_jd = new CsvListReader(new FileReader(csv_file),CsvPreference.STANDARD_PREFERENCE);
			
		
			CSVReader read = new CSVReader(new FileReader(csv_file)); //to get the value of val(number of records)
			List<String> columns; // for JD match count
			
			List<String[]> cells; 					//for val
			cells = read.readAll(); 				//for val
			int val = cells.size(); 				//for val
			int count = 0;
			int download=0;
			
	
			int search_skill = 0;
			String[] header = {"Title","Location","present job title","Resume updated","Education","Target job title","Desired status","Desrired job type","Desired job salary","Relocation","Authorization","Keyword Match","Details","Time","Name","Jobdiva match count","Download status","Download count"};
         	CSVWriter w = new CSVWriter(new FileWriter(outpath,true));
	        w.writeNext(header);
	        w.close();
	    	
myloop1:	while((columns=reader_jd.read())!=null){
			int len = columns.size();
			/*int match = len-1;	
			int txtfile = len-2;*/
				
myloop:	    for(count=0; count<1 ; count++)
		{
	
	       try{
	    	   String jd = columns.get(len-1);
	        if(jd.equals("0")) { System.out.println("JobDiva match Count: " + jd);} 
	        else { System.out.println("More than 0 found ||MOving onto next candidate "); 
	        columns.add("Already exists in JD");
	        columns.add(String.valueOf(download));
			CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
		    writer.write(columns);
		    writer.close();
	        break myloop;}}
	       catch(NullPointerException e){
	        
	      //  if(jd.equals(" ")) { System.out.println("JobDiva match Count: " + jd);} 
	        System.out.println("Blank record found ||MOving onto next candidate ");
	        columns.add("Blank record found");
	        columns.add(String.valueOf(download));
			CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
		    writer.write(columns);
		    writer.close();
	        break myloop;}
	        
	        
		if(download==dcount){System.out.println("Completed " + dcount + " download  for the above search string|| move to next"); 
		CSVWriter writer = new CSVWriter(new FileWriter(dfile,true));
		int rleft = search_skill-dcount;
		String[] dvalue={filename,"Target Download completed: Records left for download: ",String.valueOf(rleft),String.valueOf(download)};
        writer.writeNext(dvalue);
        writer.close();break myloop1;}
		
		else{System.out.println("Still downloading from the same file||downloading count is " + download);}
		
		String name = columns.get(0); 
		String t_name = columns.get(len-2);
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
       
        boolean b=false;
        	Thread.sleep(2000);
        	try{
        	String display = driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_resumeRow")).getAttribute("style");
        	System.out.println(display);
        	if(!(display.equals("display: none;")))
        	{b=true;
        	}  
        	else {b=false;} }   
        	catch(NoSuchElementException e)
        	{
        	if(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolder1_errorLabel")).isDisplayed())
        	{ System.out.println("Record not  found");
        		columns.add("Record not found");
        		columns.add(String.valueOf(download));
        		CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
        		writer.write(columns);
        		writer.close();
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolder1_backLink")).click();
        		break myloop;
        	} else {System.out.println("Some other error occured");}
        	}
       
        if(b)
        { 	System.out.println("Word download found? " + b);   
        	Thread.sleep(4000);
        		
        		driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_lblDownloadResume']/div/a[2]")).click();
		        //driver.findElement(By.linkText("Word")).click();
		        download++;
		        String[] info = {name,title, "Word document",String.valueOf(download)};
		        System.out.println("Downloaded Word");
		        columns.add("Downloaded Word");
		        columns.add(String.valueOf(download));
				CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
			    writer.write(columns);
			    writer.close();
		        List<WebElement> divs = driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_lblResumeBody']/div"));
		  
		      List l1 = new ArrayList();
		      
		      for(WebElement e : divs){

		        l1.add(e.getText());
		        //writer1.write(e.getText());		        
		        
		      }  
		      FileWriter writer1 = new FileWriter("C:/Users/Developer/Documents/monster_resume_2nd/25jan/txt_res_27/" + t_name +".txt"); 
		      List<String> lines = l1;
		      for(String str: lines) {
		        writer1.write(str);
		        writer1.write("\n");
		        
		      }writer1.close();
		      
		      
		      //System.out.println(cell[key]);
		     
		     driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
		     Thread.sleep(5000);}
        else 
        {
        	
         		Thread.sleep(7000);
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_controlDetailTop_lblForward")).click();
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_BatchActions_forwardResumeMenuItem_ForwardResume1_txtbox_ForwardResume_Email")).clear();
        		driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_ctl00_BatchActions_forwardResumeMenuItem_ForwardResume1_txtbox_ForwardResume_Email")).sendKeys("vaishali@mlwiz.com");
        		Thread.sleep(2000);
        		driver.findElement(By.linkText("Send")).click();
        		System.out.println("NO download Available|mailed");
        		download++;
        		String[] info = {name,title, "NO download Available|mailed",String.valueOf(download)};
        		columns.add("NO download Available|mailed");
		        columns.add(String.valueOf(download));
				CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
			    writer.write(columns);
			    writer.close();
    	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
    	        Thread.sleep(5000);
       
        }}
        
       
        else {
        	String[] info = {name,title, "Exact match not found|Skipped download",String.valueOf(download)};
        	columns.add("Exact match not found|Skipped download");
	        columns.add(String.valueOf(download));
			CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
		    writer.write(columns);
		    writer.close();
	        Thread.sleep(5000);
        	break myloop;}
        }
        else {String[] info = {name,title, "Already viewed|downloaded",String.valueOf(download)};
        columns.add("Already viewed|downloaded");
        columns.add(String.valueOf(download));
		CsvListWriter writer = new CsvListWriter(new FileWriter(outpath,true), CsvPreference.STANDARD_PREFERENCE);
	    writer.write(columns);
	    writer.close();Thread.sleep(5000);
    	break myloop;}}
	   search_skill++;
	   if(search_skill==val){System.out.println("FILE CHANGE");
  	 CSVWriter writer = new CSVWriter(new FileWriter(dfile,true));
		String[] dvalue={filename,"EOF: val: ", String.valueOf(val),String.valueOf(download)};
       writer.writeNext(dvalue);
       writer.close();
       break myloop1;}
		    else{System.out.println("No file change " + search_skill);}}
			
			read.close(); reader_jd.close();
			System.out.println("File change Occured||wait for 10 sec");
			Thread.sleep(10000);
			}
			driver.findElement(By.xpath("//*[@id='nonResponsiveTopMenu']/ul/li[5]/a")).click();
			
			driver.findElement(By.linkText("Sign Out")).click();
       
  		driver.close();
       reader1.close();
       
	}}
     	


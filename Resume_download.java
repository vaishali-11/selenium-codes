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
public class Resume_download {
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Developer\\Downloads\\chromedriver_win32\\chromedriver.exe");
		   //Download setting
		String downloadFilepath = "C:/Users/Developer/Documents/monster_resume_2nd/24nov/resume/";
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
		//driver.findElement(By.id("ctl00_ctl00_cphHeader_navResHeader_loginBar_btnLogIn")).click();
		
			Thread.sleep(30000);
			driver.findElement(By.id("top_menu_372")).click();;
			//Select canddown = new Select(driver.findElement(By.id("top_menu_372")));
			driver.findElement(By.id("top_menu_372_373")).click();
			//canddown.selectByVisibleText("Search Resumes");
			/*driver.findElement(By.id("wtvN_Candidates")).click();
			Thread.sleep(5000);
	     	driver.findElement(By.id("wtvS_SearchRes")).click();
	     	Thread.sleep(3000);*/
	     	driver.findElement(By.id("standardResumeSearch")).click();
	     	Select dropdown1 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_minimumDateRange")));
	        dropdown1.selectByVisibleText("Today");
	        Select dropdown2 = new Select(driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_maximumDateRange")));
	        dropdown2.selectByVisibleText("1 month");
	        driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_cphBody_topSearchBtn")).click();
	        String[] file;
			int j =0;
			String dfile="C:/Users/Developer/Documents/monster_resume_2nd/24nov/dfile.csv";
			String stringfile = "C:/Users/Developer/Documents/monster_resume_2nd/24nov/search_strings1.csv";
			CSVReader reader1 = new CSVReader(new FileReader(stringfile));
			while((file=reader1.readNext())!=null)
			{   
				System.out.println("File change Occured||wait for 10 sec");
				Thread.sleep(10000);
				String filename=file[j];
			//	int val = Integer.parseInt(file[1]);
				int dcount = Integer.parseInt(file[2]);
				System.out.println("downloading from " + filename + ".csv");
			String csv_file = "C:/Users/Developer/Documents/monster_resume_2nd/24nov/JD/monster_skill_" + filename + ".csv";
			String outpath = "C:/Users/Developer/Documents/monster_resume_2nd/24nov/JD/monster_skill_" + filename + ".csv";
			
			//To get the last column of the file : jobdiva match result
			
			CsvListReader reader_jd = new CsvListReader(new FileReader(csv_file),CsvPreference.STANDARD_PREFERENCE);
			
			//System.out.println("Downloading from  file download_" + csv_file);
			
			CSVReader reader = new CSVReader(new FileReader(csv_file));
			CSVReader read = new CSVReader(new FileReader(csv_file)); //to get the value of val(number of records)
			List<String> columns = reader_jd.read(); // for JD match count
			int len = columns.size();  				 // for JD match count
			List<String[]> cells; 					//for val
			cells = read.readAll(); 				//for val
			int val = cells.size(); 				//for val
			String[] cell;
			int count = 0;
			int download=0;
			int match = len-1;	
			int txtfile = len-2;
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
		String t_name = cell[txtfile];
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
       
        boolean b;
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
        		
        		driver.findElement(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_lblDownloadResume']/div/a[2]")).click();
		        //driver.findElement(By.linkText("Word")).click();
		        download++;
		        String[] info = {name,title, "Word document",String.valueOf(download)};
		        System.out.println("Downloaded Word");
		        CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
		        writer.writeNext(info);
		        writer.close();
		        List<WebElement> divs = driver.findElements(By.xpath("//*[@id='ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderRight_lld_Detail_resumeDetailTabStrip_controlResumeTab_lblResumeBody']/div"));
		  
		      List l1 = new ArrayList();
		      
		      for(WebElement e : divs){

		        l1.add(e.getText());
		        //writer1.write(e.getText());		        
		        
		      }  
		      FileWriter writer1 = new FileWriter("C:/Users/Developer/Documents/monster_resume_2nd/24nov/text_res/" + t_name +".txt"); 
		      List<String> lines = l1;
		      for(String str: lines) {
		        writer1.write(str);
		        writer1.write("\n");
		        
		      }writer1.close();
		      
		      
		      //System.out.println(cell[key]);
		     
		     driver.findElement(By.id("ctl00_ctl00_ContentPlaceHolderBase_ContentPlaceHolderLeft_msgBackToSearch")).click();
		     Thread.sleep(5000);}
        else {
        	
         		Thread.sleep(7000);
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
       
        }}
       
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
	   search_skill++;}reader.close(); read.close(); reader_jd.close();}
			driver.findElement(By.xpath("//*[@id='nonResponsiveTopMenu']/ul/li[5]/a")).click();
			driver.findElement(By.xpath("//*[@id='settingsDetailsMenu']/li[7]/a")).click();
        //driver.findElement(By.id("ctl00_ctl00_cphHeader_navHeader_loginBar_lnkLogout")).click();
  		driver.close();
       reader1.close();
       
	}}
     	

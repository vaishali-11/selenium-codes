package message;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


import java.util.concurrent.TimeUnit;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;



public class company_names {

	@SuppressWarnings("unused")
 
	
	public static void main(String[] args) throws IOException, InterruptedException, ElementNotInteractableException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\HIGROOVE-3\\Downloads\\geckodriver-v0.16.1-win64\\geckodriver.exe");
		WebDriver driver;
		//String link;

		String baseurl = "https://www.google.com/";
		String CSV_file = "C:\\Users\\HIGROOVE-3\\Downloads\\company_input.csv";
		String outpath = "C:\\Users\\HIGROOVE-3\\Downloads\\company_url_final.csv";
				
		
		//Creating web browser object
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		driver.get(baseurl);
		
		
		CSVReader reader = new CSVReader(new FileReader(CSV_file));
			String[] cell;
			
			//Reading through out the file until the last record
			
			while((cell=reader.readNext())!=null){
myloop:		for(int i=0;i<1;i++){
	    String company_name = cell[i];
		driver.findElement(By.id("lst-ib")).sendKeys(company_name);
		 driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		 boolean b;
		    try {
		    	driver.findElement(By.name("btnG"));
		    	b = true;
		    } catch (ElementNotInteractableException e) {
		        b = false;
		     
		    }
		    if(b){
		    	
		driver.findElement(By.name("btnG")).click();
		    }
		    else {
		    	System.out.println(b);
		    	driver.findElement(By.name("btnK")).click();
		    }
		//Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		WebElement ele2 = driver.findElement(By.xpath("(//h3[@class='r']/a)[3]"));
		WebElement ele1 = driver.findElement(By.xpath("(//h3[@class='r']/a)[2]"));
		 WebElement ele = driver.findElement(By.xpath("(//h3[@class='r']/a)[1]"));
		 System.out.println(ele.getAttribute("href"));	
		 String[] status = { company_name, ele.getAttribute("href"), ele1.getAttribute("href") ,ele2.getAttribute("href")};
		// ele.click();
		 CSVWriter writer = new CSVWriter(new FileWriter(outpath,true));
	        writer.writeNext(status);
	        writer.close();
	    	
	        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
		//Thread.sleep(5000);
		driver.get(baseurl);
		Thread.sleep(2000);
}
			}reader.close();
	}

	
}
		
		
package message;

import java.util.List;
import com.opencsv.CSVReader;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class split {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String infile="C:/Users/Developer/Documents/monster_resume_2nd/25jan/search_strings1.csv";
		CSVReader read = new CSVReader(new FileReader(infile));
		int count = 0;
		String[] cells;
		
		File  f = new File("C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD/");
		System.out.println(f.mkdir());
		
		while((cells = read.readNext())!=null)
		{	 /*int len = cells.length;
	         System.out.println(cells[len-1]);}*/
			String search_skill = cells[count];
		String csv_file = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/scrape/monster_skill_" + search_skill + ".csv";
		
		
		String out_file = "C:/Users/Developer/Documents/monster_resume_2nd/25jan/JD/monster_skill_"+ search_skill + ".csv";	
			System.out.println(csv_file);
		CsvListReader reader = new CsvListReader(new FileReader(csv_file), CsvPreference.STANDARD_PREFERENCE);
			
		List<String> columns; //= reader.read();
		
myloop:		while ((columns = reader.read()) != null) {
			int len = columns.size();
	
	
			//System.out.println(len);
		    String title = columns.get(0);
		
		    try{
		    /*if (title.equals(""))
	         { break myloop;}
	         else {}*/
		   
			String[] part = title.split("—");
			String part1 = part[0];
			columns.add(part1);
				
		    //System.out.println("Output: " + columns);
			CsvListWriter writer = new CsvListWriter(new FileWriter(out_file,true), CsvPreference.STANDARD_PREFERENCE);
		    writer.write(columns);
		    writer.close();
			}
			catch (NullPointerException e)
			{
				System.out.println("NUll RECORD FOUND");
				//break myloop;
			}
			
		}
		reader.close();
		
		}
		read.close();	
	}

}

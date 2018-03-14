package message;

import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.prefs.CsvPreference;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Header_add {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String infile="C:/Users/Developer/Documents/monster_resume_2nd/27dec/search_strings1.csv";
		CSVReader read = new CSVReader(new FileReader(infile));
		int count = 0;
		String[] cells;
		while((cells = read.readNext())!=null)
		{	
			String search_skill = cells[count];
		String csv_file = "C:/Users/Developer/Documents/monster_resume_2nd/27dec/JD1/monster_skill_" + search_skill + ".csv";
		String out_file = "C:/Users/Developer/Documents/monster_resume_2nd/27dec/JD1/monster_skill_" + search_skill + ".csv";	
			System.out.println(csv_file);
	//	CSVReader reader = new CSVReader(new FileReader(csv_file));
		
		/*	
		//List<String> columns = reader.read();
		int len = columns.size();
		int z_count = 0;
		String[] cell;
		System.out.println(len);
myloop:		while ((cell = reader.readNext()) != null) {
	//System.out.println(len);
		   String title = cell[len-1];
		    System.out.println(title);
		   
		   try{
		    if (title.equals("0"))
	         { z_count++;}
	         else {System.out.println("0 not found");}
		   	}	
		   			
			
			catch (NullPointerException e)
			{
				System.out.println("NUll RECORD FOUND");
				//break myloop;
			}
}
		/*CSVWriter writer = new CSVWriter(new FileWriter(out_file,true));
		String[] result = {search_skill,String.valueOf(z_count)};
	    writer.writeNext(result);
	    writer.close();
		reader.close();
		read1.close();*/
		}
		read.close();	
	}

}

		

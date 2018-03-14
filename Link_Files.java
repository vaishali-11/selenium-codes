package message;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class Link_Files {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String infile1 = "C:\\Users\\Developer\\Desktop\\job_classification\\file_info.csv";
		String infile2 = "C:\\Users\\Developer\\Desktop\\job_classification\\candidate_info.csv";
		CSVReader reader = new CSVReader(new FileReader(infile1));
		//CSVReader read = new CSVReader(new FileReader(infile2));
		
		String[] cell;
		String outfile = "C:\\Users\\Developer\\Desktop\\job_classification\\candidates_info.csv";
		
		while((cell=reader.readNext())!=null){
    		//String id = cell[0];
			String job_name = cell[1];
    		String file_name = cell[2];
    		String[] part = file_name.split("_");
			//int id = NumberUtils.toInt(part[0]);
    		String id = part[0];
			System.out.println("file1 " + id);
    		String[] cells;
    		CSVReader read = new CSVReader(new FileReader(infile2));
loop1:   	while((cells=read.readNext())!=null) {
    			String id1 = cells[0];
    			String name = cells[1];
    			String email = cells[2];
    			System.out.println("file2 " + id1);
    			try{
    				if(!(id.equals(id1)))
    			
    			{ System.out.println("MATCH NOT FOUND");
    			
    			}
    			
    			else {String[] info = {id1,name,email,file_name,job_name};
    			System.out.println("MATCH FOUND");
    			CSVWriter Writer = new CSVWriter(new FileWriter(outfile,true));
    			Writer.writeNext(info);
			     Writer.close();
    			break loop1;}}
    			catch(NumberFormatException e) { e.printStackTrace();}
    		
    		}read.close();
    		}reader.close();
		}

	}



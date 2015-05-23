import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Momento{
	
	Scanner scanner;
	BufferedWriter output;
	File file = null;

	Momento(File file) {
		this.file = file;
		}
	
	protected String read()  {
		if(file.exists()){
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		    while (scanner.hasNext())
				return scanner.next();
			}
		return null;
	}
	
	protected void setState( String stateValue)  {
	    try {
			output = new BufferedWriter(new FileWriter(file));
			output.write(stateValue);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
			
	}
	}

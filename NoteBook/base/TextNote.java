package base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class TextNote extends Note implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String content;
	
	public TextNote(String title){
		super(title);
		content="";
	}
	
	public TextNote(File f) {
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}
	
	private String getTextFromFile(String absolutePath) {
		String result = "";
		File file = null;
		Scanner input = null;
		try{
			file = new File(absolutePath);
			input = new Scanner(file);
			while(input.hasNextLine()){
				content = content.concat(input.nextLine());
			}
			input.close();
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public TextNote(String title,String content){
		super(title);
		this.content = content;
	}
	
	public String getContent(){
		return content;
	}
	
	public void setContent(String newContent){
		this.content = new String(newContent);
	}
	
	public void exportTextToFile(String pathFolder) {
		File file = null;
		PrintWriter pw = null;
		try{
			if(pathFolder != "")
				file = new File(pathFolder + File.separator + getTitle().replaceAll(" ","_") + ".txt");
			else
				file = new File(getTitle().replaceAll(" ","_") + ".txt");
			pw = new PrintWriter(file);
			pw.print(content);
			pw.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
}

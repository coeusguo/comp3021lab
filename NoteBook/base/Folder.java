package base;

import java.util.ArrayList;
public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		notes = new ArrayList<Note>();
		this.name = name;
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Note> getNotes(){
		return notes;
	}
	
	public String toString(){
		int nText = 0;
		int nImage = 0;
		
		for(Note n:notes){
			if(n instanceof TextNote){
				nText++;
			}
			else{
				nImage++;
			}
		}
		
		return name + ":" + nText + ":" + nImage;
	}
	
	public boolean equals(Folder folder){
		if(name.equals(folder.name)){
			return true;
		}
		return false;
	}
}

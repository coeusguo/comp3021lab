package base;

import java.util.ArrayList;

public class Folder {
	private ArrayList<Note> notes;
	private String name;
	
	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();
	}
	
	public void addNote(Note note){
		notes.add(note);
	}
	
	public String getName(){
		return this.name;
	}
	
	public ArrayList<Note> getNotes(){
		return this.notes;
	}
	
	public boolean equals(Folder folder){
		if(this.name.equals(folder.name)){
			return true;
		}
		return false;
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
}

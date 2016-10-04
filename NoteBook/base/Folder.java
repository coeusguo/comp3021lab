package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Folder implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	public void sortNotes(){
		List<Note> listnote = new ArrayList<Note>(notes);
		Collections.sort(listnote);
		notes = (ArrayList<Note>)listnote;
	}
	
	public List<Note> searchNotes(String keywords){
		String key = keywords.toLowerCase();
		String[] keys = key.split(" ");
		for(int i = 0;i < keys.length;i++)
			keys[i] = keys[i].trim();
		
		List<Note> resultnotes = new ArrayList<Note>();
		
		for(Note n:notes){
			TextNote tn = null;
			if(n instanceof TextNote){
				tn = (TextNote)n;
				int i = 0;
				while(i < keys.length){
					if(i == keys.length - 1){
						if(tn.getTitle().matches(".*"+keys[i]+".*")||tn.getContent().matches(".*"+keys[i]+".*")){
							resultnotes.add(tn);
						}
						i++;
					}
					else if((i == keys.length-3 )&&( keys[i+1].equals("or"))){
						if((tn.getTitle().matches(".*"+keys[i]+".*")||tn.getContent().matches(".*"+keys[i]+".*"))||(tn.getTitle().matches(".*"+keys[i+2]+".*")||tn.getContent().matches(".*"+keys[i+2]+".*")))
							resultnotes.add(tn);
						i++;
					}
					else if((keys[i+1].equals("or"))&&(i != keys.length-2)){
						if(!((tn.getTitle().matches(".*"+keys[i]+".*")||tn.getContent().matches(".*"+keys[i]+".*"))||(tn.getTitle().matches(".*"+keys[i+2]+".*")||tn.getContent().matches(".*"+keys[i+2]+".*")))){
							break;
						}
						else{
							i = i + 3;
							while(keys[i].equals("or")&&i<keys.length){
								i += 2;
							}
						}
					}
					else{
						if(!(tn.getTitle().matches(".*"+keys[i]+".*")||tn.getContent().matches(".*"+keys[i]+".*"))){
							break;
						}
						i++;
					}
				}
			}
			else{
				int i = 0;
				while(i < keys.length){
					if(i == keys.length-1){
						if(match(keys[i],n.getTitle())){
							resultnotes.add(n);
						}
						i++;
					}
					else if((i == keys.length - 3 )&&( keys[i+1].equals("or"))){
						if(match(keys[i],n.getTitle().toLowerCase()) || match(keys[i+2],n.getTitle().toLowerCase())){
							resultnotes.add(n);
						}
						i++;
					}
					else if((keys[i+1].equals("or"))&&(i != keys.length-2)){
						if(!(match(keys[i],n.getTitle().toLowerCase()) || match(keys[i+2],n.getTitle().toLowerCase())) ){
							break;
						}
						else{
							
							i = i + 3;
							while(keys[i].equals("or")&&i<keys.length){
								i = i + 2;
							}
						}
					}
					else{
						if(!(match(keys[i],n.getTitle() ))){
							break;
						}
						else{
							i++;
						}
					}
				}
			}
		}
		return resultnotes;
	}
	
	private boolean match(String key,String a){
		if(a.matches(".*"+key+".*")||a.matches(".*"+key)||a.matches(key+".*")){
			return true;
		}
		return false;
	}
}

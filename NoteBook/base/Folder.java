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
					//ending case 1
					if(i == keys.length - 1){
						if(tn.getTitle().toLowerCase().contains(keys[i])||tn.getContent().toLowerCase().contains(keys[i]))
							resultnotes.add(tn);
						break;
					}
					//ending case 2:the last 3 element in keys array is 'key1' 'or' 'key2'
					else if((i == keys.length-3 )&&( keys[i+1].equals("or"))){
						if((tn.getTitle().toLowerCase().contains(keys[i])||tn.getContent().toLowerCase().contains(keys[i]))||(tn.getTitle().toLowerCase().contains(keys[i+2])||tn.getContent().toLowerCase().contains(keys[i+2])))
							resultnotes.add(tn);
						break;
					}
					//searching case 1: key[i] 'or' keys[i+2]
					else if((keys[i+1].equals("or"))){
						//neither of the two keys matched in the title or content
						if(!((tn.getTitle().toLowerCase().contains(keys[i])||tn.getContent().toLowerCase().contains(keys[i]))||(tn.getTitle().toLowerCase().contains(keys[i+2])||tn.getContent().toLowerCase().contains(keys[i+2])))){
							if(keys[i+3] == "or"&& i+3 < keys.length){
								//keys:A or B or C ,now both A and B are wrong,move to C
								//if C can be found matched,the whole search case is still satisfied
								i += 4;
								continue;
							}
							else
								break;
						}
						else{
							//at least one of the keys matched in the title or content
							i += 3;
							//special case A or B or C,now A or B is correct,A or B or C must me correct,and so does A or B or C or D....and so on
							while(keys[i].equals("or")&&i<keys.length){
								i += 2;
							}
						}
					}
					//ordinary searching case
					else{
						if(!(tn.getTitle().toLowerCase().contains(keys[i])||tn.getContent().toLowerCase().contains(keys[i]))){
							break;
						}
						i++;
					}
				}
			}
			//image note
			else{
				int i = 0;
				while(i < keys.length){
					//ending case 1
					if(i == keys.length-1){
						if(n.getTitle().toLowerCase().contains(keys[i])){
							resultnotes.add(n);
						}
						break;
					}
					//ending case 2,the same as ending case 2 of text note
					else if((i == keys.length - 3 )&&( keys[i+1].equals("or"))){
						if(n.getTitle().toLowerCase().contains(keys[i]) || n.getTitle().toLowerCase().contains(keys[i+2])){
							resultnotes.add(n);
						}
						break;
					}
					//searching case 1
					else if(keys[i+1].equals("or")){
						if(!(n.getTitle().toLowerCase().contains(keys[i]) || n.getTitle().toLowerCase().contains(keys[i+2])) ){
							if(keys[i+3] == "or"&& i+3 < keys.length){
								//keys:A or B or C ,now both A and B are wrong,move to C
								//if C can be found matched,the whole search case is still satisfied
								i += 4;
								continue;
							}
							else
								break;
						}
						else{
							//the same as text note case
							i += 3;
							while(keys[i].equals("or")&&i<keys.length){
								i += 2;
							}
						}
					}
					//ordinary searching case 
					else{
						if(!(n.getTitle().toLowerCase().contains(keys[i]))){
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
	

}

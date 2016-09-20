package base;

import java.util.ArrayList;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;
	
	public NoteBook(){
		folders = new ArrayList<Folder>();
	}
	
	public boolean createTextNote(String foldername,String title){
		TextNote note = new TextNote(title);
		return insertNote(foldername,note);
	}
	
	public boolean createTextNote(String foldername,String title,String content){
		TextNote note = new TextNote(title,content);
		return insertNote(foldername,note);
	}
	
	public boolean createImageNote(String foldername,String title){
		ImageNote note = new ImageNote(title);
		return insertNote(foldername,note);
	}
	
	public boolean insertNote(String foldername,Note note){
		Folder f = null;
		
		for(Folder f1:folders){
			if(f1.equals(new Folder(foldername)))
				f = f1;
		}
		
		if(f == null){
			f = new Folder(foldername);
			f.addNote(note);
			folders.add(f);
			return true;
		}
		
		for(Note n:f.getNotes()){
			if(n.getTitle().equals(note.getTitle())){
				System.out.println("Creating note " + note.getTitle() + " under folder " + foldername + " failed");
				return false;
			}
		}
		
		f.addNote(note);
		return true;
	}
	
	public ArrayList<Folder> getFolders(){
		return folders;
	}
	
	public void sortFolders(){
		for(Folder f:folders){
			f.sortNotes();
		}
	}
	
	public List<Note> searchNotes(String keywords){
		List<Note> resultnote = new ArrayList<Note>();
		for(Folder f:folders){
			resultnote.addAll(f.searchNotes(keywords));
		}
		return resultnote;
	}
}

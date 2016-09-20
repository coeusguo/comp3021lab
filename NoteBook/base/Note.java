package base;

import java.util.Date;

public class Note implements Comparable<Note> {
	private Date date;
	private String title;
	
	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}
	
	public String getTitle(){
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	@Override
	public int compareTo(Note note){
		if(date.getTime()<note.date.getTime())
			return -1;
		else if(date.getTime() == note.date.getTime())
			return 0;
		else
			return 1;
	}
	
	public String toString(){
		return date.toString() + "\t" + title;
	}
}

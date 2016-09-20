package base;
import java.util.ArrayList;
import java.util.List;

import base.Folder;
import base.Note;
import base.NoteBook;

public class testLab3 {
	public static void main(String args[]){
		
		NoteBook nb = new NoteBook();
		nb.createTextNote("Java", "COMP30213021 syllabus", "Be able to implement object-oriented concepts in Java.");
		nb.createTextNote("Java", "course information", "Introduction to Java Programming. Fundamentals include language syntax, object-oriented programming, inheritance, interface, polymorphism, exception handling, multithreading and lambdas.");
		nb.createTextNote("Lab", "Lab requirement","Each lab has 2 credits, 1 for attendence and the other is based the completeness of your lab.");
		
		nb.createImageNote("Course", "Time Tables");
		nb.createImageNote("Assignment", "Assignment Lists");
		nb.createImageNote("CSE", "Lab Session");
		nb.createTextNote("Java", "marking scheme", "The quizzes and lab grades will be given based on your attendance in quizze and lab, respectively");
		nb.createImageNote("Java", "java Attendance Checking");
		
		nb.sortFolders();
		int findex = 0;
		for (Folder folder : nb.getFolders()) {
			System.out.println("Folder " + findex++ + ":" + folder.toString());
			List<Note> notes = folder.getNotes();
			int nindex = 0;
			for (Note note : notes) {
				System.out.println("--" + nindex++ + ":" + note.toString());
			}
		}
		
		List<Note> notes = nb.searchNotes("java or LAB attendance OR SESSION");
		System.out.println("Search Results:");
		if (notes == null || notes.size() == 0) {
			System.out.println("No Search Results");
		} else  {
			for (Note note : notes) {
				System.out.println(note.toString());
			}
		}
		
		
	}
}
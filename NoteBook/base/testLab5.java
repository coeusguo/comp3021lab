package base;


import java.io.File;
import base.NoteBook;
import base.TextNote;

public class testLab5 {
	public static void main(String args[]) throws Exception{
		
		NoteBook nb = new NoteBook();
		nb.createTextNote("Java", "COMP30213021 syllabus", "Be able to implement object-oriented concepts in Java.");
		nb.createTextNote("Java", "course information", "Introduction to Java Programming. Fundamentals include language syntax, object-oriented programming, inheritance, interface, polymorphism, exception handling, multithreading and lambdas.");
		nb.createTextNote("Lab", "Lab requirement","Each lab has 2 credits, 1 for attendence and the other is based the completeness of your lab.");
		
		nb.createImageNote("Course", "Time Tables");
		nb.createImageNote("Assignment", "Assignment Lists");
		nb.createImageNote("CSE", "Lab Session");
		nb.createTextNote("Java", "marking scheme", "The quizzes and lab grades will be given based on your attendance in quizze and lab, respectively");
		nb.createImageNote("Java", "java Attendance Checking");
		
		if(nb.save("test.ser")){
			System.out.println("saved notebook to file test.ser");
		}else{
			throw new Exception("Your code has some problems.......");
		}
		
		NoteBook nb2 = new NoteBook("test.ser");
		if(!nb2.getFolders().toString().equals("[Java:3:1, Lab:1:0, Course:0:1, Assignment:0:1, CSE:0:1]")){
			throw new Exception("Your code has some problems.......");
		}else{
			System.out.println("your notebook has been loaded");
		}
		
		TextNote t = (TextNote) nb2.getFolders().get(0).getNotes().get(0);
		t.exportTextToFile("");
		
		if(new File(((TextNote) nb2.getFolders().get(0).getNotes().get(0)).getTitle().replaceAll(" ", "_")+".txt").exists()){
			System.out.println("TextNote exported in " + new File(((TextNote) nb2.getFolders().get(0).getNotes().get(0)).getTitle().replaceAll(" ", "_")+".txt").getAbsolutePath());
		}else{
			throw new Exception("Your code has some problems.......");
		}
		
		System.out.println("Congratulations, your code is OK");

		
	}
}
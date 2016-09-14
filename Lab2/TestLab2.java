package base;


public class TestLab2 {

		public static void main(String args[]){
			
			NoteBook nb = new NoteBook();
			boolean b;
			int numSucceed = 0;
			b = nb.createTextNote("life", "lunch");
			if (b) numSucceed++;
			b = nb.createImageNote("life", "selfie");
			if (b) numSucceed++;
			b = nb.createTextNote("life", "dinner");
			if (b) numSucceed++;
			b = nb.createTextNote("life", "breakfast");
			if (b) numSucceed++;
			b = nb.createImageNote("life", "travelling");
			if (b) numSucceed++;
			b = nb.createTextNote("life", "dinner");
			if (b) numSucceed++;

			b = nb.createTextNote("study", "Math");
			if (b) numSucceed++;
			b = nb.createImageNote("study", "Art");
			if (b) numSucceed++;
			b = nb.createTextNote("study", "Chinese");
			if (b) numSucceed++;
			b = nb.createTextNote("study", "English");
			if (b) numSucceed++;
			b = nb.createImageNote("study", "Photography");
			if (b) numSucceed++;
			b = nb.createTextNote("study", "Biology");
			if (b) numSucceed++;
			b = nb.createTextNote("study", "Photography");
			if (b) numSucceed++;
			
			System.out.println("Successfully created " + numSucceed + " notes");
			for(Folder f : nb.getFolders()){
				System.out.println(f.toString());
			}
		}
	}
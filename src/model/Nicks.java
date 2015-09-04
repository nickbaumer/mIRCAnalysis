package model;
import java.util.ArrayList;

public class Nicks {
	
	static ArrayList<String> nickList = new ArrayList<String>();
	
	public Nicks(){
		 
	}

	static int nickCount = 0;
	
	public void addNick(String nick){
		int nickListSize = nickList.size();
		boolean addNick = true;
		int i = 0;
		while (i < nickListSize){
			if (nickList.get(i).toString().equals(nick)) {
				addNick = false;
			}
			i++;
		}
		if (addNick == true) {
			nickList.add(nick);
		}
		
		
	}
	

	
	
	
}
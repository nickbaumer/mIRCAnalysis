package model;
import java.util.ArrayList;

public class Nicks {
	
	static ArrayList<String> nickList = new ArrayList<String>();
	
	public Nicks(){
		 
		//create nick 0 to catch unknown nicks
		String defaultNick = "default";
		nickList.add(defaultNick);
		
	}

	
	// assuming we never have more than 1,000 nicks to analyse
	static int[] nickCount = new int[1000];
	
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
	
	public static int nickCount(){
		
		int count = nickList.size();
		return count;
		
	}
	
	public static void incrementNick(String nick){
		
		++nickCount[nickID(nick)];
		
	}
	
	public static int numberOfLines(String nick){
		return nickCount[nickID(nick)];
	}
	
	public static int nickID(String nick){
		int myID = 0;
		for (int i=0; i < nickList.size(); i++){
			if (nickList.get(i).toString().equals(nick)) {
				myID = i;
			}
		}
		return myID;
	}
	
	public static String nickName(int nick){
		return nickList.get(nick).toString();
	}
	
	public static String allLineCounts(){
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		String nickName;
		int nickLines;
		for (int i= 0; i < nickCount() ; i++){
			nickName = nickName(i);
			nickLines = numberOfLines(nickName);
			line = nickName + " has said " + nickLines + " lines.";
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
	}
	
}
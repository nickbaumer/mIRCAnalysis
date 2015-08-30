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
	
	public void AddNick(String nick){
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
	
	public static int NickCount(){
		
		int count = nickList.size();
		return count;
		
	}
	
	public static void IncrementNick(String nick){
		
		++nickCount[NickID(nick)];
		
	}
	
	public static int NumberOfLines(String nick){
		return nickCount[NickID(nick)];
	}
	
	public static int NickID(String nick){
		int myID = 0;
		for (int i=0; i < nickList.size(); i++){
			if (nickList.get(i).toString().equals(nick)) {
				myID = i;
			}
		}
		return myID;
	}
	
	public static String NickName(int nick){
		return nickList.get(nick).toString();
	}
	
	public static String AllLineCounts(){
		StringBuffer stringBuffer = new StringBuffer();
		String line = null;
		String nickName;
		int nickLines;
		for (int i= 0; i < NickCount() ; i++){
			nickName = NickName(i);
			nickLines = NumberOfLines(nickName);
			line = nickName + " has said " + nickLines + " lines.";
			stringBuffer.append(line);
			stringBuffer.append("\n");
		}
		return stringBuffer.toString();
	}
	
}
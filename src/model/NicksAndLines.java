package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SortDesc implements Comparator<NicksAndLines>{
	@Override
	public int compare(NicksAndLines nick1, NicksAndLines nick2) {
		return nick2.getLines().compareTo(nick1.getLines());
	}
}

class SortAsc implements Comparator<NicksAndLines>{
	@Override
	public int compare(NicksAndLines nick1, NicksAndLines nick2) {
		return nick1.getLines().compareTo(nick2.getLines());
	}
}

public class NicksAndLines {
	
	static ArrayList<String> nickList = new ArrayList<String>();

	String nick = new String();
	Long lines = new Long(0);
	
	public NicksAndLines() {
		
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
			this.nick = nick;
	}

	public Long getLines() {
		return lines;
	}

	public void setLines(Long lines) {
		this.lines = lines;
	}
	
	public void incrementLines() {
		this.lines++;
	}
	
	public int totalNicks() {
		return nickList.size();
	}
	
	public static ArrayList<NicksAndLines> sortDesc(ArrayList<NicksAndLines> nicks) {
		Collections.sort(nicks, new SortDesc());
		return nicks;
	}
}

package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

class AliasComparator {
	
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	private String alias;
	private String nick;
	
}

public class Alias {
	
	// initial thoughts
	// pass in a nickname, return the actual nickname
	
	
	
	public static String check (String nickname) {
		File file = new File("c:\\temp\\alias.txt");
		ArrayList<AliasComparator> aliases = new ArrayList<AliasComparator>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				// grab first character
				String firstChar = line.substring(0,1);
				if (firstChar.equals("#")) {
					// ignore
				} else {				
				String [] arr = line.split(" ");
				String alias = null;
				String nick;
				int count = 0;
				for (String s : arr) {
					AliasComparator comparator = new AliasComparator();
						if (count == 0) {
							alias = s;
						}
						nick = s;
						comparator.setAlias(alias);
						comparator.setNick(nick);
						aliases.add(comparator);
						count++;
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// testing output
		for (int i = 0; i<aliases.size();i++) {
			System.out.println("Nick: "+aliases.get(i).getNick()+" & Alias: "+aliases.get(i).getAlias());	
		}
		return "testing";
		
	}

}

package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;

import model.Alias;

public class ReadQuote {
	
	public static void load(String file) {
		File incFile = new File(file);
		FileReader fileReader;
		Alias aliases = new Alias();
		try {
			fileReader = new FileReader(incFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
				
				int endOfNick = line.indexOf(" ");
				String nickname = line.substring(0,endOfNick);
				nickname = aliases.check(nickname);
				int endOfDate = line.indexOf(" ", endOfNick+1);
				String date = line.substring(endOfNick+1,endOfDate);
				Long longDate = Long.getLong(date);
				Instant instant = Instant.ofEpochSecond(longDate);
				String quote = line.substring(endOfDate+1,line.length());
				System.out.println("Nickname: "+nickname+" Date: "+date+" Quote: "+quote);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

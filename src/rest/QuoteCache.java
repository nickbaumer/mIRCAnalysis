package rest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import app.ReadLog;
import model.*;
import model.Reddit;

public enum QuoteCache {
    INST;
    
	private static ArrayList<QuoteLine> quotes = ReadLog.Load();
	
    private QuoteCache() {
    }
    
    public MircStringPaul quotesToday() {
    	quotes = ReadLog.Load();
    	LocalDate date = LocalDate.now();
    	Long output = Statistics.DailyQuotes(quotes, date);
    	System.out.println("quotecache output: " + output);
    	String inputString = "Total quotes for today: " + output.toString();
    	MircStringPaul string = new MircStringPaul(inputString);
    	return string;
    }
    
    public MircString randomSingleQuote() {
    	String string = Statistics.RandomSingleQuote(quotes);
    	String outputString = "A random quote: " + string;
    	MircString mircString = MircString.of(outputString);
    	return mircString;
    }
    
    public MircString lanCountdown() {
    	LocalDateTime lan = LocalDateTime.of(2015, 8, 28, 18, 0, 0);
    	LocalDateTime now = LocalDateTime.now();
    	Long seconds = now.until(lan, ChronoUnit.SECONDS);
		int totalSeconds = (60 * 60 * 24 * 365 * 1000);
		BigDecimal bigSeconds = new BigDecimal(totalSeconds);
		BigDecimal secondsLeft = new BigDecimal(seconds);
		BigDecimal millennia = secondsLeft.divide(bigSeconds, 50, RoundingMode.CEILING);
    	String inputString = "There are " + seconds.toString() + " seconds to LAN - you'd better be prepared for it, mofos. Or, if you're Ragnar, there are " + millennia + " millennia left.";
    	if (seconds < 0) {
    		seconds = Math.abs(seconds);
    		inputString = "LAN has been running for " + seconds.toString() + " seconds! Kick ass!";
    	}
    	MircString mircString = MircString.of(inputString.toString());
    	return mircString;
    }
    
    public MircString gangnamStyle() {
    	MircString mircString = new MircString();
    	try {
    		File file = new File("c:\\temp\\gangnamstyle.txt");
	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> stringBuffer = new ArrayList<String>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
			Long numberQuotes = stringBuffer.stream()
    				.collect(Collectors.counting());	
    		Random rand = new Random();
    		int  n = rand.nextInt(stringBuffer.size()) + 0;
    		mircString = MircString.of(stringBuffer.get(n));
    	}
			catch (IOException e) {
				e.printStackTrace();
		}
    		
			return mircString;
    }
    
    public MircString whatDoesTheFoxSay() {
    	MircString mircString = new MircString();
    	try {
    		File file = new File("c:\\temp\\whatdoesthefoxsay.txt");
	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> stringBuffer = new ArrayList<String>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
			Long numberQuotes = stringBuffer.stream()
    				.collect(Collectors.counting());	
    		Random rand = new Random();
    		int  n = rand.nextInt(stringBuffer.size()) + 0;
    		mircString = MircString.of(stringBuffer.get(n));
    	}
			catch (IOException e) {
				e.printStackTrace();
		}
    		
			return mircString;
    }
    
    public MircString reddit(){
    	MircString mircString = new MircString();
    	String s = "all";
    	String redditOutput = Reddit.redditTopLink(s);
    	mircString = MircString.of(redditOutput);
    	return mircString;
    }
    
}
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
import java.util.Random;
import app.ReadLog;
import model.*;

public enum QuoteCache {
    INST;
    
	// filename on statb0t
	static String file =  "\\\\192.168.0.5\\c\\mirc\\logs\\#dagodz.QuakeNet.log";
	private static ArrayList<QuoteLine> quotes = ReadLog.load(file);
	
    private QuoteCache() {
    }
    
    public MircString quotesToday() {
    	quotes = ReadLog.load(file);
    	LocalDate date = LocalDate.now();
    	Long output = Statistics.dailyQuotes(quotes, date);
    	System.out.println("quotecache output: " + output);
    	String inputString = "Total quotes for today: " + output.toString();
    	MircString string = MircString.of(inputString);
    	return string;
    }
    
    public MircString randomSingleQuote() {
    	String string = Statistics.randomSingleQuote(quotes);
    	String outputString = "A random quote: " + string;
    	MircString mircString = MircString.of(outputString);
    	return mircString;
    }
    
    public MircString lanCountdown() {
    	LocalDateTime lan = LocalDateTime.of(2016, 4, 29, 18, 0, 0);
    	LocalDateTime now = LocalDateTime.now();
    	Long seconds = now.until(lan, ChronoUnit.SECONDS);
		int totalSeconds = (60 * 60 * 24 * 365 * 1000);
		BigDecimal bigSeconds = new BigDecimal(totalSeconds);
		BigDecimal secondsLeft = new BigDecimal(seconds);
		BigDecimal millennia = secondsLeft.divide(bigSeconds, 50, RoundingMode.HALF_UP);
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
    		File file = new File("gangnamstyle.txt");
	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> stringBuffer = new ArrayList<String>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
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
    		File file = new File("whatdoesthefoxsay.txt");
	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> stringBuffer = new ArrayList<String>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
			Random rand = new Random();
    		int  n = rand.nextInt(stringBuffer.size()) + 0;
    		mircString = MircString.of(stringBuffer.get(n));
    	}
			catch (IOException e) {
				e.printStackTrace();
		}
    		
			return mircString;
    }
    
    public MircString reddit(String subreddit){
    	MircString mircString = new MircString();
    	String redditOutput = Reddit.redditTopLink(subreddit);
    	mircString = MircString.of(redditOutput);
    	return mircString;
    }
    
    public MircString stats(String duration) {
    	// valid durations
    	// all, year, month, week, day
    	MircString mircString = new MircString();
    	// default
    	LocalDate dateFrom = LocalDate.now().minusYears(10);
    	if (duration.equals("year")){
    		dateFrom = LocalDate.now().minusYears(1);
    	} else if (duration.equals("month")) {
    		dateFrom = LocalDate.now().minusMonths(1);
    	} else if (duration.equals("week")) {
    		dateFrom = LocalDate.now().minusWeeks(1);
    	} else if (duration.equals("day")) {
    		dateFrom = LocalDate.now().minusDays(1);
    	}
    	LocalDate dateTo = LocalDate.now();
    	ArrayList<NicksAndLines> unsortedOutput = Statistics.topNicks(quotes, dateFrom, dateTo);
    	ArrayList<NicksAndLines> output = NicksAndLines.sortDesc(unsortedOutput);
    	StringBuffer stringBuffer = new StringBuffer();
    	String initial = "Quote summary: ";
    	stringBuffer.append(initial);
    	for (int i=0;i<output.size() && i<10;i++){
    		if (i>0) { 
    			stringBuffer.append(", "); 
    			}
    		String tempString = output.get(i).getNick() + " - " + output.get(i).getLines();
    		stringBuffer.append(tempString);
    	}
    	mircString = MircString.of(stringBuffer.toString());
    	return mircString;
    }
    
    public MircString joke() {
    	MircString mircString = new MircString();
    	try {
    		File file = new File("jokes.txt");
	    	FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> stringBuffer = new ArrayList<String>();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.add(line);
			}
			fileReader.close();
			Random rand = new Random();
    		int  n = rand.nextInt(stringBuffer.size()) + 0;
    		mircString = MircString.of(stringBuffer.get(n));
    	}
			catch (IOException e) {
				e.printStackTrace();
		}
    		
			return mircString;
    }
    
}
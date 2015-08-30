package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class QuoteLine {
	
	// counter for number of objects created
	
	static int count = 0;
	
	// An ID for each object
	
	int id;
	
	private LocalDateTime quotedatetime;
	private String quotenick;
	private String quotetext;
	
	private static LocalDateTime latestQuote;
	private static LocalDateTime earliestQuote;
	
	public QuoteLine(){

		// Every time the constructor runs, increment count.
		
		count++;
		
		// Set the ID
		
		id = count;
		
	}
	
	public int getId(){
		return id;
	}
	
	public void Add(LocalDateTime datetime, String nick, String quote){
		quotedatetime = datetime;
		quotenick = nick;
		quotetext = quote;
		
		// attempt to track latest date
		
		if (id == 1){
			latestQuote = datetime;
			earliestQuote = datetime;
		}
		
		if (datetime.isAfter(latestQuote)){
			latestQuote = datetime;
		}
		
		// attempt to track earliest date
		
		if (datetime.isBefore(earliestQuote)){
			earliestQuote = datetime;
		}
		
	}
	
	public String Nick(){
		return quotenick;
	}
	
	public String Quote(){
		return quotetext;
	}
	
	public LocalDateTime Date(){
		return quotedatetime;
	}
	
	public static LocalDateTime EarliestQuote(){
		return earliestQuote;
	}
	
	public static LocalDateTime LatestQuote(){
		return latestQuote;
	}
	
}

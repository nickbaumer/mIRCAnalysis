package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class Statistics {
	
	public static Long dailyQuotes(ArrayList<QuoteLine> quotes, LocalDate date) {
		
		// Returns the total number of quotes on a specified day
		
		// Convert Date to DateTime
		
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDateTime dateTimeAfter = LocalDateTime.of(date, midnight);
		LocalDateTime dateTimeBefore = dateTimeAfter.plusDays(1);
		
		// Filter collection of quotes
		
		Long output = quotes.stream()
		.filter(e -> ((QuoteLine) e).date().isAfter(dateTimeAfter))
		.filter(e -> ((QuoteLine) e).date().isBefore(dateTimeBefore))
		.collect(Collectors.counting());
		
		// Return Long
		
		return output;
		
	}
	
	public static Long quotesFromTo(ArrayList<QuoteLine> quotes, LocalDate dateFrom, LocalDate dateTo) {
		
		// Returns the total number of quotes within a date range
		
		// Convert Date to DateTime
		
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDateTime dateTimeAfter = LocalDateTime.of(dateFrom, midnight);
		LocalDateTime dateTimeBefore = LocalDateTime.of(dateTo.plusDays(1), midnight);
		
		// Filter collection of quotes
		
		Long output = quotes.stream()
		.filter(e -> ((QuoteLine) e).date().isAfter(dateTimeAfter))
		.filter(e -> ((QuoteLine) e).date().isBefore(dateTimeBefore))
		.collect(Collectors.counting());
		
		// Return Long
		
		return output;
		
	}

	public static Long nickQuotes(ArrayList<QuoteLine> quotes, String nick) {
		
		// Returns the total number of quotes from a specified nickname
		
		// Filter collection of quotes
		
		Long output = quotes.stream()
		.filter(e -> ((QuoteLine) e).nick().equals(nick))
		.collect(Collectors.counting());
		
		// Return Long
		
		return output;
		
	}
	
	public static Long dailyNickQuotes(ArrayList<QuoteLine> quotes, String nick, LocalDate date) {
		
	// Returns the total number of quotes from a specified nickname on a specified date
	
	// Convert Date to DateTime
	
		LocalTime midnight = LocalTime.MIDNIGHT;
		LocalDateTime dateTimeAfter = LocalDateTime.of(date, midnight);
		LocalDateTime dateTimeBefore = dateTimeAfter.plusDays(1);
	
	// Filter collection of quotes
		
		Long output = quotes.stream()
		.filter(e -> ((QuoteLine) e).nick().equals(nick))
		.filter(e -> ((QuoteLine) e).date().isAfter(dateTimeAfter))
		.filter(e -> ((QuoteLine) e).date().isBefore(dateTimeBefore))
		.collect(Collectors.counting());
		
		// Return Long
		
		return output;
		
	}
	
	public static String mostSaidWord(ArrayList<QuoteLine> quotes){
		
		ArrayList<String> words = new ArrayList<String>();
		//String[] excludedWords = new String[]{"a", "the", "and", "but", "I", "you", "is", "yes", "no", "on", "off", "of", "for", "to", "it", "that", "in", "be", "not", "have", "my", "if", "just"};
		String[] excludedWords = new String[]{"like", "I'm", "it's", "just", "I", "a", "about", "above", "above", "across", "after", "afterwards", "again", "against", "all", "almost", "alone", "along", "already", "also","although","always","am","among", "amongst", "amoungst", "amount",  "an", "and", "another", "any","anyhow","anyone","anything","anyway", "anywhere", "are", "around", "as",  "at", "back","be","became", "because","become","becomes", "becoming", "been", "before", "beforehand", "behind", "being", "below", "beside", "besides", "between", "beyond", "bill", "both", "bottom","but", "by", "call", "can", "cannot", "cant", "co", "con", "could", "couldnt", "cry", "de", "describe", "detail", "do", "done", "down", "due", "during", "each", "eg", "eight", "either", "eleven","else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every", "everyone", "everything", "everywhere", "except", "few", "fifteen", "fify", "fill", "find", "fire", "first", "five", "for", "former", "formerly", "forty", "found", "four", "from", "front", "full", "further", "get", "give", "go", "had", "has", "hasnt", "have", "he", "hence", "her", "here", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "him", "himself", "his", "how", "however", "hundred", "ie", "if", "in", "inc", "indeed", "interest", "into", "is", "it", "its", "itself", "keep", "last", "latter", "latterly", "least", "less", "ltd", "made", "many", "may", "me", "meanwhile", "might", "mill", "mine", "more", "moreover", "most", "mostly", "move", "much", "must", "my", "myself", "name", "namely", "neither", "never", "nevertheless", "next", "nine", "no", "nobody", "none", "noone", "nor", "not", "nothing", "now", "nowhere", "of", "off", "often", "on", "once", "one", "only", "onto", "or", "other", "others", "otherwise", "our", "ours", "ourselves", "out", "over", "own","part", "per", "perhaps", "please", "put", "rather", "re", "same", "see", "seem", "seemed", "seeming", "seems", "serious", "several", "she", "should", "show", "side", "since", "sincere", "six", "sixty", "so", "some", "somehow", "someone", "something", "sometime", "sometimes", "somewhere", "still", "such", "system", "take", "ten", "than", "that", "the", "their", "them", "themselves", "then", "thence", "there", "thereafter", "thereby", "therefore", "therein", "thereupon", "these", "they", "thickv", "thin", "third", "this", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "top", "toward", "towards", "twelve", "twenty", "two", "un", "under", "until", "up", "upon", "us", "very", "via", "was", "we", "well", "were", "what", "whatever", "when", "whence", "whenever", "where", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whoever", "whole", "whom", "whose", "why", "will", "with", "within", "without", "would", "yet", "you", "your", "yours", "yourself", "yourselves", "the"};
		for (int i=0; i < quotes.size(); i++){
			
			String s = quotes.get(i).quote();
			String [] arr = s.split(" ");
			
			for (String ss : arr) {
				words.add(ss);
			}
			
		}
		
		Long wordCount = 0L;
		String maxWord = new String();
		Long maxCount = 0L;
		
		for (int i=0; i < words.size(); i++) {
			String word = words.get(i).toString();
			wordCount = words.stream()
			.filter(e-> ((String) e).equals(word))
			.collect(Collectors.counting());
		
		if (wordCount > maxCount && (!Arrays.asList(excludedWords).contains(word) && !word.substring(0,1).equals("!")) && word.length()>2){
			maxCount = wordCount;
			maxWord = word;
		}
		}
		
		return maxWord;
		
	}
	
	public static String randomQuote(ArrayList<QuoteLine> quotes, String nick){
		// This object expects an object<QuoteLine> containing all lines
		Long totalQuotes = quotes.stream()
		.filter(e -> ((QuoteLine) e).nick().equals(nick))
		.collect(Collectors.counting());
		// Select a random number to return
		Random rand = new Random();
		int  n = rand.nextInt(totalQuotes.intValue()) + 0;
		
		ArrayList<QuoteLine> randomQuote = (ArrayList<QuoteLine>) quotes.stream()
				.filter(e -> ((QuoteLine) e).nick().equals(nick))
				.collect(Collectors.toList());
				
		
		return randomQuote.get(n).quote();
		
	}
	
	public static String randomSingleQuote(ArrayList<QuoteLine> quotes){
		// This object expects an object<QuoteLine> containing all lines
		Long totalQuotes = quotes.stream()
		.collect(Collectors.counting());
		// Select a random number to return
		Random rand = new Random();
		int  n = rand.nextInt(totalQuotes.intValue()) + 0;
		
		ArrayList<QuoteLine> randomQuote = (ArrayList<QuoteLine>) quotes.stream()
				.collect(Collectors.toList());
				
		
		return randomQuote.get(n).nick() + ": " + randomQuote.get(n).quote();
		
	}
	
}


}
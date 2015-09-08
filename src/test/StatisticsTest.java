package test;

import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;
import app.ReadLog;
import model.NicksAndLines;
import model.QuoteLine;
import model.Statistics;

public class StatisticsTest {
	
	String testFile = "c:\\temp\\test.txt";
	LocalDate testDate = LocalDate.of(2015,  8, 3);
	LocalDate testDate2 = LocalDate.of(2015, 8, 5);
	ArrayList<QuoteLine> quotes = ReadLog.load(testFile);
	String nick = "Vyper";
	
	@Test
	public void dailyQuotes() {
		Long output = Statistics.dailyQuotes(quotes, testDate);
		Assert.assertTrue(output == 510);
	}
	
	@Test
	public void nickQuotes() {
		Long output = Statistics.nickQuotes(quotes, nick);
		Assert.assertTrue(output == 746);
	}
	
	@Test
	public void dailyNickQuotes() {
		Long output = Statistics.dailyNickQuotes(quotes, nick, testDate);
		Assert.assertTrue(output == 105);
	}
	
	@Test
	public void mostSaidWord() {
		String output = Statistics.mostSaidWord(quotes);
		Assert.assertTrue(output.equals("lol"));
	}
	
	@Test
	public void randomQuote() {
		String output = Statistics.randomQuote(quotes, nick);
		Assert.assertTrue(output.getClass() == String.class);
	}
	
	@Test
	public void randomSingleQuote() {
		String output = Statistics.randomSingleQuote(quotes);
		Assert.assertTrue(output.getClass() == String.class);
	}
	
	@Test
	public void quotesFromToTest() {
		Long output = Statistics.quotesFromTo(quotes, testDate, testDate2);
		Assert.assertTrue(output == 1831);
	}
	
	@Test
	public void uniqueNicksTest() {
		ArrayList<String> nicks = Statistics.uniqueNicks(quotes);
		Assert.assertTrue(nicks != null);
	}
	
	@Test
	public void topNicksTest() {
		ArrayList<NicksAndLines> nicks = Statistics.topNicks(quotes, testDate, testDate2);
		ArrayList<NicksAndLines> nicksSorted = NicksAndLines.sortDesc(nicks);
		for (int i=0; i<nicksSorted.size();i++) {
			System.out.println(nicks.get(i).getNick() + " - " + nicks.get(i).getLines());
		}
		Assert.assertTrue(nicks != null);
	}

}
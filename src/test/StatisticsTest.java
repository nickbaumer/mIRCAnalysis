package test;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertThat;

import app.ReadLog;
import model.QuoteLine;
import model.Statistics;

public class StatisticsTest {
	
	String testFile = "c:\\temp\\test.txt";
	LocalDate testDate = LocalDate.of(2015,  8, 3);
	ArrayList<QuoteLine> quotes = ReadLog.Load(testFile);
	String nick = "Vyper";
	
	@Test
	public void DailyQuotes() {
		Long output = Statistics.DailyQuotes(quotes, testDate);
		Assert.assertTrue(output == 510);
	}
	
	@Test
	public void NickQuotes() {
		Long output = Statistics.NickQuotes(quotes, nick);
		Assert.assertTrue(output == 746);
	}
	
	@Test
	public void DailyNickQuotes() {
		Long output = Statistics.DailyNickQuotes(quotes, nick, testDate);
		Assert.assertTrue(output == 105);
	}
	
	@Test
	public void MostSaidWord() {
		String output = Statistics.MostSaidWord(quotes);
		Assert.assertTrue(output.equals("lol"));
	}
	
	@Test
	public void RandomQuote() {
		String output = Statistics.RandomQuote(quotes, nick);
		Assert.assertTrue(output.getClass() == String.class);
	}
	
	@Test
	public void RandomSingleQuote() {
		String output = Statistics.RandomSingleQuote(quotes);
		Assert.assertTrue(output.getClass() == String.class);
	}

}
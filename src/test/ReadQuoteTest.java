package test;

import app.ReadQuote;
import org.junit.Test;

public class ReadQuoteTest {
	@Test
	public void loadTest(){
		ReadQuote.load("\\\\192.168.0.5\\c\\mirc\\quotes.txt");
	}
}

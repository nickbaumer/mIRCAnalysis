package test;

import app.ReadQuote;
import org.junit.Test;
import junit.framework.Assert;

public class ReadQuoteTest {
	@Test
	public void loadTest(){
		ReadQuote.load("\\\\192.168.0.5\\c\\mirc\\quotes.txt");
	}
}

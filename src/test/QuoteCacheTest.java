package test;

import org.junit.Test;

import model.MircString;
import rest.QuoteCache;

public class QuoteCacheTest {
	
	@Test
	public void quotesTodayTest(){
		MircString output = QuoteCache.INST.quotesToday();
		output.toString();
		
	}

}

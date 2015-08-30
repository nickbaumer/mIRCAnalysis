package test;

import static org.junit.Assert.*;

import org.junit.Test;

import junit.framework.Assert;
import model.MircString;

public class MircStringTest {
	@Test
	public void BlankConstructorTest() {
		MircString mircString = new MircString();
		String toString = mircString.toString();
		Assert.assertTrue(toString.equals("MircString{string=null}"));
	}
	
	@Test
	public void MircStringToStringTest() {
		MircString mircString = MircString.of("testing");
		String toString = mircString.toString();
		Assert.assertTrue(toString.equals("MircString{string=testing}"));
	}

}

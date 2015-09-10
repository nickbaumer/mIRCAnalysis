package test;

import model.Alias;
import org.junit.Test;

import junit.framework.Assert;

public class AliasTest {
	@Test
	public void checkTest() {
		String output = Alias.check("Dave");
		Assert.assertTrue(output.getClass() == String.class);
	}
}
package test;

import model.Alias;
import org.junit.Test;

import junit.framework.Assert;

public class AliasTest {
	@Test
	public void checkTest() {
		Alias alias = new Alias();
		String output = alias.check("RagnarKarlssonTheSecond");
		Assert.assertTrue(output.equals("Ragnar"));
	}
}
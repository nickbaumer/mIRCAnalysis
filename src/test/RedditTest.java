package test;
import junit.framework.Assert;
import model.Reddit;
import org.junit.Test;

public class RedditTest {
	
	@Test
	public void RedditTopLinkTest(){
		String subreddit = "all";
		String output = Reddit.redditTopLink(subreddit);
		Assert.assertTrue(output.getClass().equals(String.class));
		Assert.assertTrue(output.contains("http://www.reddit.com/r/"));
	}

}
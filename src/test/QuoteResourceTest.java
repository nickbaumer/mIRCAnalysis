package test;
import org.junit.Test;
import static com.jayway.restassured.RestAssured.expect;

import static org.hamcrest.Matchers.*;

public class QuoteResourceTest {
	
	@Test
	public void QuotesTodayTest(){
		expect().
		statusCode(200).
		body("string", containsString("Total quotes for today:")).
		when().
		get("http://localhost:2001/stats/tasks/today");
	}
	
	@Test
	public void RandomQuoteTest(){
		expect().
		statusCode(200).
		body("string", containsString("A random quote:")).
		when().
		get("http://localhost:2001/stats/tasks/random");
	}
}

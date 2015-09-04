package test;
import org.junit.Test;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.*;
import org.hamcrest.Matchers.*;
import junit.framework.Assert;
import static com.jayway.restassured.RestAssured.expect;

import static com.jayway.restassured.RestAssured.get;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.filter.Filter;
import com.jayway.restassured.filter.FilterContext;
import com.jayway.restassured.specification.FilterableRequestSpecification;
import com.jayway.restassured.specification.FilterableResponseSpecification;
import com.jayway.restassured.specification.RequestSpecification;
import org.junit.Ignore;
import java.io.PrintStream;
import java.io.StringWriter;
import java.net.URLEncoder;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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

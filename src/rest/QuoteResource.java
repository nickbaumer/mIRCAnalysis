package rest;

import java.util.Date;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import model.*;

@Path("tasks")
public class QuoteResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("today")
	public Response quotesToday() {
		MircString quote = QuoteCache.INST.quotesToday();
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("random")
	public Response randomQuote() {
		MircString quote = QuoteCache.INST.randomSingleQuote();
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("LAN")
	public Response lanCountdown() {
		MircString quote = QuoteCache.INST.lanCountdown();
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("oppa")
	public Response gangnamStyle() {
		MircString quote = QuoteCache.INST.gangnamStyle();
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("reddit/{subreddit}")
	public Response reddit(@PathParam("subreddit") String subreddit) {
		System.out.println("subreddit string is: "+subreddit);
		MircString quote = QuoteCache.INST.reddit(subreddit);
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("whatdoesthefoxsay")
	public Response whatDoesTheFoxSay() {
		MircString quote = QuoteCache.INST.whatDoesTheFoxSay();
		Response resp = null;
		if (quote.toString() != "") {
			resp = Response.ok(quote).build();
			// TaskCache.INST.incrementTask(task);
		} else {
			resp = Response.noContent().build();
		}
		return resp;
	}
}
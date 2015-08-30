package app;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import model.MircString;

public class JerseyClient {

	public static void main(String[] args) {
	    Client client = ClientBuilder.newClient();
	    List<URI> locations = new ArrayList<>();
	        
	    WebTarget target = client.target("http://localhost:2001").path("api").path("tasks");
	        
	    //Response postResponse = target.request(MediaType.APPLICATION_JSON)
	  //          .post(Entity.json(MircString.of("Testclient1", "Using test client to create", new Date())));
	  //  locations.add(postResponse.getLocation());
	        
	  //  postResponse = target.request(MediaType.APPLICATION_JSON)
	 //           .post(Entity.json(MircString.of("Testclient2", "Using test client to create", new Date())));
	//    locations.add(postResponse.getLocation());
	        
	 //   postResponse = target.request(MediaType.APPLICATION_JSON)
	 //           .put(Entity.json(MircString.of("Testclient3", "Using test client to create", new Date())));
	  // locations.add(postResponse.getLocation());
	        
	        
	    // Get first one
	    WebTarget getTarget = client.target(locations.get(0));
	    Response getResponse = getTarget.request(MediaType.APPLICATION_JSON).get();
	    
	    System.out.println(getResponse.getStatus());
	    System.out.println(getResponse.readEntity(MircString.class));
	       
	    // Change second one
	    getTarget = client.target(locations.get(1));
	    getResponse = getTarget.request(MediaType.APPLICATION_JSON).get();
	    MircString aTask = getResponse.readEntity(MircString.class);
	   // aTask.setTitle("This title is changed");
	    client.target(locations.get(1)).request(MediaType.APPLICATION_JSON).put(Entity.json(aTask));
	        
	    // Delete thirs one
	    client.target(locations.get(2)).request().delete();
	        
	    WebTarget allTarget = client.target("http://localhost:2001").path("api").path("tasks");
	        
	    GenericType<List<MircString>> genericType = new GenericType<List<MircString>>() {};
	        
	    List<MircString> all = allTarget.request(MediaType.APPLICATION_JSON).get(genericType);
	    for (MircString task : all) {
	        System.out.println(task);
	    }
	 }


}


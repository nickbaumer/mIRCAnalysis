package app;
import org.glassfish.jersey.server.ResourceConfig;

import app.JerseyServer;

public class Main extends ResourceConfig {

	public static void main(String[] args) {
		JerseyServer.main(args);;
	}

}
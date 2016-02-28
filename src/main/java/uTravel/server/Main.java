package uTravel.server;

import io.swagger.client.ApiClient;

public class Main {

	public static void main(String[] args) {
		try 
		{
			//SimpleHttpServer.Start();
			UtravelHttpServer.Start(args);
			ApiClient client = new ApiClient();
			System.out.println(client.getBasePath());
			//System.out.println(client.invokeAPI(path, method, queryParams, body, binaryBody, headerParams, formParams, accept, contentType, authNames, returnType)());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

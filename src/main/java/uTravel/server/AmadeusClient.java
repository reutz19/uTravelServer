package uTravel.server;

import java.util.Map;
import io.swagger.client.ApiClient;

public class AmadeusClient {
	
	private ApiClient client;
	
	public AmadeusClient(){
		client = new ApiClient();
	}
	
	public String SendRequest(Map<String,String> parms)
	{
		//client.
		//String reqUrl = http://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?origin=IST&destination=BOS&departure_date=2015-10-15&return_date=2015-10-21&number_of_results=3&apikey=<your API key> 
		return "";
	}
}

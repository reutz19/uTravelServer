package uTravel.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import io.swagger.client.ApiClient;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.TypeRef;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.AffiliateSearchResponse;
import io.swagger.client.model.AffiliateSearchResult;
import io.swagger.client.model.AirportInformation;
import io.swagger.client.model.LowFareSearchResponse;
import io.swagger.client.model.LowFareSearchResult;
import uTravel.server.UtravelHttpServer.HandleResponse;

// Page : /utravel/search
// Handles requests for route search 
public class SearchHandler implements HttpHandler 
{
	private ApiClient apiClient;
	private DefaultApi defaultApi;
	private String apikey = "Qf1ykk1SmZBQzgf2f6OJrwDAPHdnNIuk";
	/*private String origin;
	private String destination;
	private String departureDate;
	private String returnDate;*/

	public SearchHandler(){
		apiClient = Configuration.getDefaultApiClient();
		defaultApi = new DefaultApi(apiClient);
	//	apiClient.setApiKey("Qf1ykk1SmZBQzgf2f6OJrwDAPHdnNIuk"); //TODO : change it to not hardcoded
	}

	public void handle(HttpExchange httpExchange) throws IOException 
	{
		Map <String,String> parms = 
				UtravelHttpServer.queryToMap(httpExchange.getRequestURI().getQuery());

		ParseResponse(parms, httpExchange);
	}

	//extract relevant parameters
	private List<Pair> getParams(String org, String dest, String dep_dt, String ret_dt)
	{							
		List<Pair> queryParams = new ArrayList<Pair>();
		
		queryParams.addAll(apiClient.parameterToPairs("", "apikey", "Qf1ykk1SmZBQzgf2f6OJrwDAPHdnNIuk"));
		queryParams.addAll(apiClient.parameterToPairs("", "origin", org));    
		queryParams.addAll(apiClient.parameterToPairs("", "destination", dest));	    
		queryParams.addAll(apiClient.parameterToPairs("", "departure_date", dep_dt));	
		queryParams.addAll(apiClient.parameterToPairs("", "return_date", ret_dt));

		return queryParams;
	}

	@SuppressWarnings({ "unused", "unused" })
	private void ParseResponse(Map<String, String> parms, HttpExchange httpExchange) 
	{
		try {
			// verify the required parameter 'origin' is set
			String origin = parms.get("origin");
			if (origin == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'origin' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'destination' is set
			String destination = parms.get("destination");
			if (destination == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'destination' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'departureDate' is set
			String departureDate = parms.get("departure_date");
			if (departureDate == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'departure_date' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'departureDate' is set
			String returnDate = parms.get("return_date");
			if (returnDate == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'return_date' when calling flightAffiliateSearch");
				return;
			}
			
			LowFareSearchResponse res = defaultApi.flightLowFareSearch(apikey, origin, destination, departureDate, returnDate, null, null, 1, 0, 0, null, null, false, 10000, "EUR", null, 250);
			//AffiliateSearchResponse res = defaultApi.flightAffiliateSearch(apikey, origin, destination, departureDate, returnDate, 1, 0, 0, null, null, 10000, "EUR", false);
		
			List<LowFareSearchResult> resList = res.getResults();
			HandleResponse.BuildResponse(resList, httpExchange);
			System.out.println(resList.get(0).toString());
		}
		catch (Exception ex){
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace();
		}
	}
	
	/*
	private void ParseResponse1(Map<String, String> parms, HttpExchange httpExchange) 
	{	
		try {
			// verify the required parameter 'origin' is set
			String origin = parms.get("origin");
			if (origin == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'origin' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'destination' is set
			String destination = parms.get("destination");
			if (destination == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'destination' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'departureDate' is set
			String departureDate = parms.get("departure_date");
			if (departureDate == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'departure_date' when calling flightAffiliateSearch");
				return;
			}
	
			// verify the required parameter 'departureDate' is set
			String returnDate = parms.get("return_date");
			if (returnDate == null) {
				UtravelHttpServer.ErrorResponse(httpExchange, "Missing the required parameter 'return_date' when calling flightAffiliateSearch");
				return;
			}
			
		    // prepare parameters for invoke
			 String path = "/flights/low-fare-search".replaceAll("\\{format\\}","json");
		    Map<String, String> headerParams = new HashMap<String, String>();
		    Map<String, Object> formParams = new HashMap<String, Object>();
		    
		    List<Pair> queryParams = getParams(origin, destination, departureDate, returnDate);
			final String[] accepts = {"application/json"};
			final String accept = apiClient.selectHeaderAccept(accepts);
			final String[] contentTypes = {};
			final String contentType = apiClient.selectHeaderContentType(contentTypes);
			String[] authNames = new String[] {};
			TypeRef<List<AirportInformation>> returnType = new TypeRef<List<AirportInformation>>(){};
			
			String result = apiClient.invokeAPI(path, "GET", queryParams, null, null, headerParams, formParams, accept, contentType, authNames, returnType);
			System.out.println("The request result is: \n" + result);
		}
		catch (Exception ex){
			System.out.println(ex.getMessage() + "\n");
			ex.printStackTrace();
		}
	}
	*/
}
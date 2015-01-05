/**
	Copyright 2014 [BFR]
	
	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at
	
	    http://www.apache.org/licenses/LICENSE-2.0
	
	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
**/
package org.bfr.querytools.google;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.bfr.querytools.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * Google <a href="https://www.google.com/get/spectrumdatabase/">spectrum database</a> client.  
 * <p>
 * This is an implementation of the <a href="https://developers.google.com/spectrum/paws/gettingstarted">Spectrum Database API</a> 
 * which uses JSON as a transport protocol. Note that the API key is hard coded, which makes this non-production code. Note that each
 * key has a daily query limit, and the service will start returning error messages after this limit has been reached.
 * 
 */
public class GoogleSpectrumQuery
{

	// API key. 
	private static String apiKey = "AIzaSyDESRe8rnVr4r-be1higwGmfAgE8nOS1CU";
	
	// Constants
	private final static String fccId = "TEST";
	private final static String mode = "MODE_2";
////////////////>>>>>>>>>>>>>>>AMJAD	
	//"query" method call counter
//	private static int queryCounter = 1 ;
	//Json array size controller 
//	private static int jsonArrController = 0;
	// Json array sizes
//	private static int[] jsonArrSizes = {1 ,2 , 4 , 8 ,16 , 32 ,64 , 128};
/////////////<<<<<<<<<<<<<<<<<<<<<<	
	private static JSONObject createFromStrings(String ... str) throws JSONException
	{
		JSONObject object = new JSONObject();
		
		for (int i=0; i<str.length; i+=2)
			object.put(str[i], str[i+1]);
		
		return object;
	}
	
	private static JSONObject createPoint(double latitude, double longitude) throws JSONException
	{
		JSONObject object = new JSONObject();

		JSONObject center = new JSONObject();
		center.put("latitude", latitude);
		center.put("longitude", longitude);
		
		JSONObject point = new JSONObject();
		point.put("center", center);
		object.put("point", point);
		
		return object;
	}
	
	private static JSONObject createAntenna() throws JSONException
	{
		JSONObject object = new JSONObject();
		
		object.put("height", 30.0);
		object.put("heightType", "AGL");
		
		return object;
	}
	
	private static JSONObject createOwner() throws JSONException
	{
		JSONObject object = new JSONObject();
		
		object.put("owner", new JSONObject());
		
		return object;
	}
	
/*	private static JSONObject createCapabilities(long ... hz) throws JSONException
	{
		JSONObject object = new JSONObject();
		
		JSONObject[] ranges = new JSONObject[hz.length/2];
		for (int i=0; i<hz.length/2; i++)
		{
			ranges[i] = new JSONObject();
			ranges[i].put("startHz", hz[i*2]);
			ranges[i].put("stopHz", hz[i*2+1]);
		}
		
		object.put("frequencyRanges", ranges);
		
		return object;
	}
*/	
	//Amjad
	//private static JSONArray createQuery(double latitude, double longitude, int count) throws JSONException
	private static JSONArray createQuery(double latitude, double longitude) throws JSONException
	{
		JSONArray arr = new JSONArray(); 
		
////////////////>>>>>>>>>>>>>>>AMJAD		
		//for (int i = 0 ; i < count ; i++)
		//	{
			//	longitude += 0.001; // The change in longitude after 100 meter
				
			//	Logger.log(String.format("LONGITUDE %f" , longitude));
/////////////<<<<<<<<<<<<<<<<<<<<<<			
				JSONObject object1 = new JSONObject();
				
				object1.put("jsonrpc", "2.0");
				object1.put("method", "spectrum.paws.getSpectrum");
				object1.put("apiVersion", "v1explorer");
				object1.put("id", "any_string");
		
				JSONObject params1 = new JSONObject();
				params1.put("type", "AVAIL_SPECTRUM_REQ");
				params1.put("version", "1.0");
				params1.put("deviceDesc", createFromStrings("serialNumber", "your_serial_number", "fccId", fccId, "fccTvbdDeviceType", mode));
				params1.put("location", createPoint(latitude, longitude));
				params1.put("antenna", createAntenna());
				params1.put("owner", createOwner());
		//		params.put("capabilities", createCapabilities(800000000, 850000000, 900000000, 950000000));		
				params1.put("key", apiKey);
				object1.put("params", params1);
		
				arr.put(object1);
			
		//	}
		
		
		return arr;
	}
	
//	public static void googleQuery() throws ClientProtocolException, IOException
//	{
//		Logger.log("query-start");
//		
//		HttpClient client = new DefaultHttpClient();
//		HttpGet request = new HttpGet("https://www.google.com");
//		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10*1000);
//		HttpConnectionParams.setSoTimeout(client.getParams(), 10*1000);
//		
//		Logger.log("query-execute");
//		HttpResponse response = client.execute(request);
//
//		// Get the response
//		InputStream input = response.getEntity().getContent();
//		byte[] block = new byte[1024 * 8];
//		
//		boolean first = true;
//		while (input.read(block)!=-1)
//		{
//			if (first)
//			{
//				Logger.log("query-first-data");
//				first = false;
//			}
//		}
//		
//		Logger.log("query-done");
//	}
	
	@SuppressWarnings("deprecation")
	public static void query(double latitude, double longitude)
	{

////////////////>>>>>>>>>>>>>>>AMJAD		
//		jsonArrController =(queryCounter % 10.0 == 0 ) ? (++jsonArrController): jsonArrController;
//		queryCounter++;
//		jsonArrController = (jsonArrController >  jsonArrSizes.length-1 ? 0 : jsonArrController);
//		
//		Logger.log(String.format("JSON_ARRAY_SIZE %d queryCounter %d Json array Size %d" ,
//				jsonArrController , queryCounter, jsonArrSizes[jsonArrController]));

/////////////<<<<<<<<<<<<<<<<<<<<<<
		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost("https://www.googleapis.com/rpc");
		HttpConnectionParams.setConnectionTimeout(client.getParams(), 10*1000);
		HttpConnectionParams.setSoTimeout(client.getParams(), 10*1000);
		
		request.addHeader("Content-Type", "application/json");
		
		try
		{
			//Amjad
//			request.setEntity(new StringEntity(createQuery(latitude, longitude, jsonArrSizes[jsonArrController] ).toString(), HTTP.UTF_8));
			request.setEntity(new StringEntity(createQuery(latitude, longitude ).toString(), HTTP.UTF_8));
			
			Logger.log(String.format("google-query-execute %.4f %.4f", latitude, longitude));
			
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			boolean first = true;
			while ((line = rd.readLine()) != null)
			{
				if (first)
				{
					Logger.log("google-query-first-data");
					first = false;
				}
				Logger.log(line);
			}

		} catch (UnsupportedEncodingException e)
		{
			Logger.log("google-query-error Unsupported Encoding: " + e.getMessage());
		} catch (JSONException e)
		{
			Logger.log("google-query-error JSON exception: " + e.getMessage());
		} catch (IOException e)
		{
			Logger.log("google-query-error i/o exception: " + e.getMessage());
		}

		
		Logger.log("google-query-done");
		
	}
	
}

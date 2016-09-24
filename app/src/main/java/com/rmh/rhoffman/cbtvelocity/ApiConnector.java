package com.rmh.rhoffman.cbtvelocity;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Class to assist in connecting to an external database.
 */
public class ApiConnector{

	/**
	 * This talks to a php script at the specified URL. That script query's the database for
	 * the necessary data, sorts it, and sends it back. The will then convert it to a JSONArray
	 * and return that array.
	 */
	public JSONArray getAllActivities(){

		HttpURLConnection urlConnection = null;
		JSONArray jsonArray = null;
		String inputStream;

		// Use try/catch block to handle exceptions, both network and JSON.
		try{

			// The url that points to the php script.
			URL url = new URL(MainActivity.DATABASE_ADDRESS + "/getAllActivities.php");
			// Define, open the connection, and connect to the database.
			urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.connect();
			InputStream stream = urlConnection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			StringBuilder builder = new StringBuilder();
			String input;

			while((input = reader.readLine()) != null){
				builder.append(input);
			}

			jsonArray = new JSONArray(builder.toString());

		} catch(IOException | JSONException e){
			// Handle any exceptions here.
			e.printStackTrace();

			return new JSONArray();

		}

		return jsonArray;

	}

}

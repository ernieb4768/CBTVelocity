package com.rmh.rhoffman.cbtvelocity;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Class to assist in connecting to an external database.
 */
public class ApiConnector{

	public JSONArray getAllActivities(){

		// URL to the php script on the server
		String url = "http://174.100.202.101/getAllActivities.php";

		// Get HttpResponse Object from the url then get HttpEntity from HttpResponse
		HttpEntity httpEntity = null;

		try{
			DefaultHttpClient defaultHttpClient = new DefaultHttpClient();

			HttpParams params = new BasicHttpParams();
			int timeoutConnection = 3000;
			HttpConnectionParams.setConnectionTimeout(params, timeoutConnection);
			int timeoutSocket = 5000;
			HttpConnectionParams.setSoTimeout(params, timeoutSocket);

			HttpGet httpGet = new HttpGet(url);
			defaultHttpClient.setParams(params);

			HttpResponse httpResponse = defaultHttpClient.execute(httpGet);

			httpEntity = httpResponse.getEntity();
		} catch(IOException e){
			e.printStackTrace();

			return new JSONArray();

		}

		// Convert the HttpEntity into JSONArray.
		JSONArray jsonArray = null;

		if(httpEntity != null){
			try{
				String entityResponse = EntityUtils.toString(httpEntity);

				jsonArray = new JSONArray(entityResponse);

				Log.d("Entity response: ", entityResponse);

			} catch(JSONException | IOException e){
				e.printStackTrace();
			}
		}

		return jsonArray;

	}

}

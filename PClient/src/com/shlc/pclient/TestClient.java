package com.shlc.pclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet("http://v.58.com/bangbang/getjobseekerlist?uid=17828615335687&qq-pf-to=pcqq.c2c");
		request.addHeader("accept", "application/json");
		try {
			HttpResponse response = client.execute(request);
			String returnedValue = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject json = new JSONObject(returnedValue);
			System.out.println(json.getString("resultmsg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

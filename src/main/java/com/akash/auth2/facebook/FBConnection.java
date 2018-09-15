package com.akash.auth2.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FBConnection {
	public static final String FB_APP_ID = "528900730583058";
	public static final String FB_APP_SECRET = "6fc85bbb214d09fe65fa5057cdcb6a6d";
	public static final String REDIRECT_URI = "http://localhost:8080/facebook/oauth2/userDetails";

	public String getFBAuthUrl() {
		String fbLoginUrl = "";
		try {
			fbLoginUrl = "http://www.facebook.com/dialog/oauth?" + "client_id="
					+ FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&scope=email,user_likes,user_friends";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbLoginUrl;
	}

	public String getFBGraphUrl(String code) {
		String fbGraphUrl = "";
		try {
			fbGraphUrl = "https://graph.facebook.com/v2.2/oauth/access_token?"
					+ "client_id=" + FBConnection.FB_APP_ID + "&redirect_uri="
					+ URLEncoder.encode(FBConnection.REDIRECT_URI, "UTF-8")
					+ "&client_secret=" + FB_APP_SECRET + "&code=" + code;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return fbGraphUrl;
	}

	public String getAccessToken(String code) {

		String accessToken = "";
		URL fbGraphURL;
		try {
			fbGraphURL = new URL(getFBGraphUrl(code));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Invalid code received " + e);
		}
		URLConnection fbConnection;
		StringBuffer b = null;
		try {
			fbConnection = fbGraphURL.openConnection();
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(
					fbConnection.getInputStream()));
			String inputLine;
			b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to connect with Facebook " + e);
		}

		System.out
				.println("===========Response for bearer token starts here=============");
		System.out.println(b);
		System.out
				.println("===========Response for bearer token ends here=============");
		JSONObject json;
		try {
			json = (JSONObject) new JSONParser().parse(b.toString());
			accessToken = (String)json.get("access_token");
		} catch (ParseException e) {
			System.out.println("unable to parse access token response : "+e);
		}
		return accessToken;
	}
}

package com.akash.auth2.facebook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class FBGraph {
	private String accessToken;

	public FBGraph(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getFBGraph() {
		String loggedInUserData = "https://graph.facebook.com/v2.2/me?"
				+ accessToken;
		return getGraphDataFromUrl(loggedInUserData);
	}

	private String getGraphDataFromUrl(String url) {
		String graph = null;
		try {
			URL u = new URL(url);
			URLConnection c = u.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));
			String inputLine;
			StringBuffer b = new StringBuffer();
			while ((inputLine = in.readLine()) != null)
				b.append(inputLine + "\n");
			in.close();
			graph = b.toString();
			System.out.println(graph);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in getting FB graph data. " + e);
		}
		return graph;
	}

	public String getProfilePic(String userId) {

		String profilePicUrl = "https://graph.facebook.com/v2.2/" + userId
				+ "/picture?format=json&method=get&redirect=false&"
				+ accessToken;
		return parseJsonForProfilePic(getGraphDataFromUrl(profilePicUrl));
	}

	private String parseJsonForProfilePic(String jsonAsString) {

		String profilePicUrl = "";
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(jsonAsString);
			JSONObject dataObject = (JSONObject) json.get("data");
			profilePicUrl = (String) dataObject.get("url");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return profilePicUrl;
	}

	public Map<String, String> getGraphData(String fbGraph) {
		System.out.println("=========Data from fb starts here===========");
		System.out.println(fbGraph);
		System.out.println("=========Data from fb ends here===========");
		Map<String, String> fbProfile = new HashMap<String, String>();
		try {
			JSONObject json = (JSONObject) new JSONParser().parse(fbGraph);
			fbProfile.put("id", (String) json.get("id"));
			fbProfile.put("first_name", (String) json.get("first_name"));
			if (json.containsKey("email"))
				fbProfile.put("email", (String) json.get("email"));
			if (json.containsKey("gender"))
				fbProfile.put("gender", (String) json.get("gender"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("ERROR in parsing FB graph data. " + e);
		}
		return fbProfile;
	}
}

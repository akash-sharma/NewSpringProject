package com.akash.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.akash.auth2.facebook.FBConnection;
import com.akash.auth2.facebook.FBGraph;

@Controller
@RequestMapping(value = "/facebook/oauth2")
public class FacebookController {

	@RequestMapping(value = "/login")
	public String login(Model model, HttpServletRequest req) {

		FBConnection fbConnection = new FBConnection();
		String fbAuthUrl = fbConnection.getFBAuthUrl();
		model.addAttribute("fbAuthUrl", fbAuthUrl);
		return "facebook/login";
	}

	@RequestMapping(value = "/userDetails")
	public String showUserData(Model model, HttpServletRequest req,
			HttpSession session) {

		String code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		session.setAttribute("accessToken", accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		model.addAttribute("fbProfileData", fbProfileData);
		return "facebook/userDetails";
	}

	@RequestMapping(value = "/profilePicUrl")
	@ResponseBody
	public String getProfilePic(Model model, HttpServletRequest req,
			@RequestParam(value = "userId", required = true) String userId,
			HttpSession session) {

		FBGraph fbGraph = new FBGraph(
				(String) session.getAttribute("accessToken"));
		return fbGraph.getProfilePic(userId);
	}
}



/**
*	Doc Url :
*	https://developers.facebook.com/docs/graph-api/reference
*
*	Scopes :
*	email
*	user_likes
*	publish_actions
*	user_friends
*	read_friendlists
*	manage_friendlists
*
*
*	Get OAuth code :
*	http://www.facebook.com/dialog/oauth?client_id={clientId}&redirect_uri={redirect_uri}&scope={comma seperated list of scopes}
*
* 	Get profile pic for user :
*	https://graph.facebook.com/v2.2/{userId}/picture?format=json&method=get&redirect=false&accessToken=accessToken
*
*	Get loggedIn user details :
*	https://graph.facebook.com/v2.2/me?accessToken={accessToken}
*
* 
*/
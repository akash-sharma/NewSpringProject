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
 * Doc Url : https://developers.facebook.com/docs/graph-api/reference
 *
 * Scopes : email user_likes publish_actions -- post,share user_friends -- list
 * of facebook friends
 *
 * read_friendlists -- grouping of friends manage_friendlists -- grouping of
 * friends
 *
 *
 * (1)Get OAuth code :
 * http://www.facebook.com/dialog/oauth?client_id={clientId}&
 * redirect_uri={redirect_uri}&scope={comma seperated list of scopes}
 *
 * (2)Get profile pic for user :
 * https://graph.facebook.com/v2.2/{userId}/picture
 * ?format=json&method=get&redirect=false&access_token=accessToken
 *
 * (3)Get loggedIn user details :
 * https://graph.facebook.com/v2.2/me?accessToken={accessToken}
 *
 * (4)Post on User profile (POST request)
 * https://graph.facebook.com/v2.2/me/feed
 * ?message=testing+facebook+api&?access_token={accessToken}
 * 
 * host:graph.facebook.com method:POST path:/v2.2/me/feed scheme:https
 * 
 * output {"id": "172202062836724_873505378039442"}
 * 
 * posted message : testing facebook api
 * 
 * 
 * (5)list of friend
 * https://graph.facebook.com/v2.2/{user_id}/friends?access_token={accessToken}
 * 
 * NOTE : This gives total number of friends for user but gives details of only
 * those users who are using this app.
 * 
 * http://stackoverflow.com/questions/23417356/facebook-graph-api-v2-0-me-
 * friends-returns-empty-or-only-friends-who-also-u
 * 
 * 
 */

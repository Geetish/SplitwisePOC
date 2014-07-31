package com.splitwiseIntegration;

import java.util.logging.Logger;
import java.io.IOException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.*;

import oauth.signpost.OAuth;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.RandomStringUtils;

@SuppressWarnings("serial")
public class MainServlet extends HttpServlet {
	private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String CONSUMER_KEY = "qCgKusFvTQATvMSezQor8BvxjE4hukafGNl7tg9v";
		String CONSUMER_SECRET = "ToEwk3QpMGrJr363okBqLKLYVqckDXE8px4UI1zZ";
		String APPLICATION_NAME = "signpost-splitwise-test";

		String SPLITWISE_REQUEST_TOKEN_URL = "https://secure.splitwise.com/api/v3.0/get_request_token";
		String SPLITWISE_ACCESS_TOKEN_URL = "https://secure.splitwise.com/api/v3.0/get_access_token";
		String SPLITWISE_AUTHORIZE_URL = "https://secure.splitwise.com/authorize"; 
		
		try{
			OAuthConsumer consumer=new DefaultOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
			OAuthProvider provider = new DefaultOAuthProvider(SPLITWISE_REQUEST_TOKEN_URL,
					SPLITWISE_ACCESS_TOKEN_URL, SPLITWISE_AUTHORIZE_URL);
			String authUrl = provider.retrieveRequestToken(consumer, "http://testenv-4pkf3id8wg.elasticbeanstalk.com/authorizeUser.do");
		        authUrl=OAuth.addQueryParameters(authUrl, OAuth.OAUTH_CONSUMER_KEY, CONSUMER_KEY,
		                "application_name", APPLICATION_NAME);
			if(req.getParameter("oauth_verifier")==null){
				System.out.println("Fetching request token...");
				resp.sendRedirect(authUrl);
		        System.out.println("Request token: " + consumer.getToken());
		        System.out.println("Token secret: " + consumer.getTokenSecret());
		   }else{
				System.out.println("OAuth_Verifier"+req.getParameter("oauth_verifier"));
				System.out.println("Request token: " + consumer.getToken());
		        System.out.println("Token secret: " + consumer.getTokenSecret());
		        provider.retrieveAccessToken(consumer, req.getParameter("oauth_verifier"));
		        System.out.println("Access token: " + consumer.getToken());
		        System.out.println("Token secret: " + consumer.getTokenSecret());
			}
		}
		catch(Exception e){
			System.out.println("*****In Exception"+e);
			e.printStackTrace();
		}
	}
}
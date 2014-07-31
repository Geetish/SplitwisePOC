package com.splitwiseIntegration;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RedirectServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		try {
			System.out.println("*******In Redirtect Servlet");
			

		} catch (Exception e) {
				System.out.println("Exception:::"+e);
		}
	}
}

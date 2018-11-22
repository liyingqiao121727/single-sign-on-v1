package com.liyq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasig.cas.client.authentication.AttributePrincipal;

/**
 * the cas client for test.
 * 
 * @author Liyingqiao
 *
 */
public class LoginController {

	@WebServlet(urlPatterns="/liyingqiao", asyncSupported=true)  
	public static class LiyingqiaoServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)  
				throws ServletException, IOException { 
			PrintWriter pw = response.getWriter();
			pw.print("hahahahahahahahahahaha");
			pw.close();
		} 

		// the cas server will visit this by http post
		// why does use http post? if login fail the cas server will return
		// error message with json. if use get method. the json need encode.
		public void doPost(HttpServletRequest request, HttpServletResponse response)  
				throws ServletException, IOException {

			Principal p = request.getUserPrincipal();
			if (null != p) {
				// you can breakpoint at here to look at the principal of the cas server return to you
				AttributePrincipal ap = (AttributePrincipal) p;
				Map m = ap.getAttributes();
			}

			/*Map<?, ?> m = request.getParameterMap();
			m.toString();
			HttpSession session = request.getSession(false);
			if (session != null) {
				Enumeration<?> e = session.getAttributeNames();
				while (e.hasMoreElements()) {
					Object object = e.nextElement();
					object.toString();
				}
				String[] s = session.getValueNames();
			}
			Enumeration<?> e = request.getAttributeNames();
			while (e.hasMoreElements()) {
				Object object = e.nextElement();
				object.toString();
			}*/
			System.err.println(request.getParameter("errors"));
			request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
		}
	}

	@WebServlet(urlPatterns="/db", asyncSupported=true)  
	public static class DBServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)  
				throws ServletException, IOException {
			request.getRequestDispatcher("/WEB-INF/a.jsp").forward(request, response); 
		}

	}

	@WebServlet(urlPatterns="/redis", asyncSupported=true)  
	public static class RedisServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		public void doGet(HttpServletRequest request, HttpServletResponse response)  
				throws ServletException, IOException {
			Principal p = request.getUserPrincipal();
			AttributePrincipal ap = (AttributePrincipal) p;
			Map m = ap.getAttributes();
			request.getRequestDispatcher("index.jsp").forward(request, response); 
		}
	}
}

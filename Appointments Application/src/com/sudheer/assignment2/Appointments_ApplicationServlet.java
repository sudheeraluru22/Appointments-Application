package com.sudheer.assignment2;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Appointments_ApplicationServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		String login = us.createLoginURL("/");
		String logout = us.createLogoutURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login", login);
		req.setAttribute("logout", logout);
		RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/main.jsp");
		dispatch.forward(req, resp);
	}
}

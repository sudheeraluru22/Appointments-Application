package com.sudheer.assignment2;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import com.google.appengine.api.users.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class createAppointmentServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		UserService service = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = service.getCurrentUser();
		if (user == null) {
			req.setAttribute("ok", null);			
		} else {
			req.setAttribute("ok", 1);
		}
		String login = service.createLoginURL("/");
		req.setAttribute("user",user);
		req.setAttribute("login", login);
		RequestDispatcher dispatch= req.getRequestDispatcher("/WEB-INF/createAppointment.jsp");
		dispatch.forward(req, resp);
	}
}
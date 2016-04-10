package com.sudheer.assignment2;

import javax.servlet.ServletException;
import javax.jdo.PersistenceManager;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import com.google.appengine.api.users.UserServiceFactory;
import javax.servlet.http.HttpServlet;
import com.google.appengine.api.datastore.Key;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class createAppointmentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		UserService service = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = service.getCurrentUser();

		String name = req.getParameter("name").trim();
		String date = req.getParameter("date").trim();
		String time = req.getParameter("time").trim();
		if (name.equals("") || date.equals("") || time.equals("")) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Wrong Input');");
			out.println("location='/create';");
			out.println("</script>");
			return;
		}
		
		String ID = u.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();;
		Key user_key = KeyFactory.createKey("User", ID);
		com.sudheer.assignment2.User user;
		Appointment ap = new Appointment(name, date, time);
		
		try {
			user = pm.getObjectById(com.sudheer.assignment2.User.class, user_key);
			ap.setParent(user);
			user.addAppointment(ap);
			pm.makePersistent(ap);
			pm.makePersistent(user);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Appointment Created Successfully');");
			out.println("location='/';");
			out.println("</script>");
		} catch (Exception e) {
			user = new com.sudheer.assignment2.User(user_key);
			pm.makePersistent(user);
			user = pm.getObjectById(com.sudheer.assignment2.User.class, user_key);
			ap.setParent(user);
			user.addAppointment(ap);
			pm.makePersistent(ap);
			pm.makePersistent(user);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Appointment Added !');");
			out.println("location='/';");
			out.println("</script>");
		} finally {
			pm.close();
		}
	}
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
		req.setAttribute("user", user);
		req.setAttribute("login", login);
		RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/createAppointment.jsp");
		dispatch.forward(req, resp);
	}
}
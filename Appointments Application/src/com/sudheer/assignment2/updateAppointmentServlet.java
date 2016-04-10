package com.sudheer.assignment2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class updateAppointmentServlet extends HttpServlet {
	UserService service;
	PrintWriter output;
	Key KEY = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User usr = service.getCurrentUser();
		output=resp.getWriter();
		String name = req.getParameter("Name");
		String date = req.getParameter("Date");
		String time = req.getParameter("Time");
		String ID = usr.getUserId();
		PersistenceManager pm = PMF.get().getPersistenceManager();;
		try {
			Appointment temp = pm.getObjectById(Appointment.class, KEY);
			temp.setName(name);
			temp.setDate(date);
			temp.setTime(time);
			pm.close();
			output.println("<script type=\"text/javascript\">");
			output.println("alert('Updated');");
			output.println("location='/';");
			output.println("</script>");

		} catch (Exception e) {
			output.println("<script type=\"text/javascript\">");
			output.println("alert('Error Occured');");
			output.println("location='/';");
			output.println("</script>");
		} finally {
			pm.close();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		service = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User usr = service.getCurrentUser();
		KEY = (Key) req.getSession().getAttribute("appointment");
		try {
			if (usr == null)
				req.setAttribute("ok", null);
			else {
				req.setAttribute("ok", 1);
				PersistenceManager pm = PMF.get().getPersistenceManager();
				Appointment temp = pm.getObjectById(Appointment.class, KEY);
				req.setAttribute("Name", temp.getName());
				req.setAttribute("Date", temp.getDate());
				req.setAttribute("Time", temp.getTime());
				req.getSession().setAttribute("appointment", null);
			}
		} catch (Exception e) {
			resp.sendRedirect("/");
		}
		String login = service.createLoginURL("/");
		req.setAttribute("user", usr);
		req.setAttribute("login", login);
		RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/updateAppointment.jsp");
		dispatch.forward(req, resp);
	}

}

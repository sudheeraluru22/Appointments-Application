package com.sudheer.assignment2;

import java.io.IOException;
import java.io.PrintWriter;
import javax.jdo.PersistenceManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class Appointments_ApplicationServlet extends HttpServlet {
	PrintWriter output;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserService service = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User usr = service.getCurrentUser();
		if (usr==null)
			return;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Key key = KeyFactory.createKey("User", usr.getUserId());
		output = resp.getWriter();
		com.sudheer.assignment2.User user = pm.getObjectById(com.sudheer.assignment2.User.class, key);
		for (int i = 0; i < user.getAppointments().size(); i++) {

			if (req.getParameter((i +"dleteButton")) != null) {
				pm.deletePersistent(user.getAppointments().get(i));
				pm.close();
				output.println("<script type=\"text/javascript\">");
				output.println("alert('Deleted');");
				output.println("location='/';");
				output.println("</script>");
				break;
			}
			else if(req.getParameter((i+"editButton"))!=null){
				req.getSession().setAttribute("appointment",user.getAppointments().get(i).getID());
				resp.sendRedirect("/update");
				break;
			}
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		UserService us = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User u = us.getCurrentUser();
		String login = us.createLoginURL("/");
		String logout = us.createLogoutURL("/");
		req.setAttribute("user", u);
		req.setAttribute("login", login);
		req.setAttribute("logout", logout);

		String ID;
		try {
			if (u != null) {
				ID = u.getUserId();
				Key key = KeyFactory.createKey("User", ID);
				PersistenceManager pm = PMF.get().getPersistenceManager();
				com.sudheer.assignment2.User user = pm.getObjectById(com.sudheer.assignment2.User.class, key);
				String data = "";
				if (user.getAppointments() != null && user.getAppointments().size() > 0) {
					data += "<div id= \"container\" align=\"center\"><form method=\"post\"><h2 align=\"center\" >YOUR APPOINTMENTS</h2><br/>";
					for (int i = 0; i < user.getAppointments().size(); i++) {
						String temp = "NAME: " + user.getAppointments().get(i).getName() + "  DATE : "
								+ user.getAppointments().get(i).getDate() + "  TIME : "
								+ user.getAppointments().get(i).getTime() + "   <========>   ";
						data += "<lable>" + temp + "</lable>";
						data += "<input type=\"submit\" name=\"" + (i + "editButton") + "\" value=\"Edit\" \\>";
						data += "<input type=\"submit\" name=\"" + (i + "dleteButton")
								+ "\" value=\"Delete\" \\><br/><br/>";
					}
					data += "</form></div>";
				}
				req.setAttribute("fetch", data);
			}
		} catch (Exception e) {
			req.setAttribute("fetch", null);
		}

		RequestDispatcher dispatch = req.getRequestDispatcher("/WEB-INF/main.jsp");
		dispatch.forward(req, resp);
	}
}

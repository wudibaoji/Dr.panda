package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

/**
 * @author Andory
 *
 */
public class CheckUser extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6577452404209062864L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		if (username == null || "".equals(username) ||
				password == null  || "".equals(password)) {
			//back to login
			req.getRequestDispatcher("WEB-INF/pages/login.html").forward(req, res);
		}
		
		UserService userService = new UserService();
		
		if (userService.checkUser(username, password)) {
			//to home page
			req.getRequestDispatcher("WEB-INF/pages/home.html").forward(req, res);
		} else {
			//back to login
			req.getRequestDispatcher("WEB-INF/pages/login.html").forward(req, res);
		}
	}
	
}

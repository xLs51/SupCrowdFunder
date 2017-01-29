package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcrowdfunder.dao.DaoFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("/SupCrowdFunder");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		try {
			// If login and password are good, set user attribute in the session
			if(DaoFactory.getUserDao().getUserByLogin(mail, password) != null) {
				// If user is an admin, set admin attribute in the session
				if(DaoFactory.getUserDao().getUserByMail(mail).isAdmin())
					request.getSession().setAttribute("usernameAdmin",mail);
				request.getSession().setAttribute("username",mail);
			}
		} catch (Exception e) {
			
		}
		response.sendRedirect("/SupCrowdFunder");
	}
}
package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/sign_up")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SignUpServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters
		String mail = request.getParameter("mail");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		
		// If the mail isn't already used
		if(!DaoFactory.getUserDao().isMailAlreadyUsed(mail)) {
			User u = new User();
			
			u.setMail(mail);
			u.setLastName(lastName);
			u.setFirstName(firstName);
			u.setAddress(address);
			u.setPassword(password);
			u.setAdmin(false);
			u.setNb_contribute(0);
			
			DaoFactory.getUserDao().add(u);
			
			// Set an user attribute to the session
			request.getSession().setAttribute("username",mail);
			
			response.sendRedirect("/SupCrowdFunder");
		}
		// Set an error attribute to display it on the sign_up page
		else  {
			String error = "Mail is already used";
			request.setAttribute("error",error);
			
			RequestDispatcher rd = request.getRequestDispatcher("/sign_up.jsp");
			rd.forward(request, response);
		}	
	}
}
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

@WebServlet("/auth/admin/addUser")
public class AddUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public AddUserServlet()
    {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/addUser.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Get the parameters
		String mail = request.getParameter("mail");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		
		// If the mail isn't already used
		if(!DaoFactory.getUserDao().isMailAlreadyUsed(mail)) {
			// Create the user
			User u = new User();
			u.setMail(mail);
			u.setFirstName(firstName);
			u.setLastName(lastName);
			u.setAddress(address);
			u.setPassword(password);
			u.setAdmin(false);
			u.setNb_contribute(0);
			
			// Add the user
			DaoFactory.getUserDao().add(u);
			
			// Redirect
			response.sendRedirect("/SupCrowdFunder/auth/admin/listUser");
		}
		else {
			String error = "Mail already used";
			request.setAttribute("error", error);
			
			RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/addUser.jsp");
			rd.forward(request, response);
		}
	}
}
package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.UnknownException;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/admin/updateUser")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get parameters
			Long id = Long.valueOf(request.getParameter("id"));		
			String mail = request.getParameter("mail");
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String address = request.getParameter("address");
			String password = request.getParameter("password");
			
			// Find the user with the id
			User u = DaoFactory.getUserDao().findById(id);
			
			// If the mail isn't already used
			if(!DaoFactory.getUserDao().isMailAlreadyUsedByUser(mail, u)) {
				u.setMail(mail);
				u.setFirstName(firstName);
				u.setLastName(lastName);
				u.setAddress(address);
				u.setPassword(password);
				
				// If checkbox admin is not checked
				if(request.getParameter("admin") == null)
					u.setAdmin(false);
				else
					u.setAdmin(true);
				
				// Update the user
				DaoFactory.getUserDao().update(u);
				
				response.sendRedirect("/SupCrowdFunder/auth/admin/listUser");
			}
			else {
				String error = "Mail already used";
				request.setAttribute("error",error);
				
				RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/listUser.jsp");
				rd.forward(request, response);
			}		
		}
		catch(UnknownException e) {
			response.sendRedirect("/SupCrowdFunder/auth/admin/listUser");
		}
	}
}
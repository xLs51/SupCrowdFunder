package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.UnknownException;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Contribution;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/admin/removeUser")
public class RemoveUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get the id
			Long id = Long.valueOf(request.getParameter("id"));
			
			// Get the user with the id
			User u = DaoFactory.getUserDao().findById(id);
			// Get all the contribution of the user
			List<Contribution> lc = DaoFactory.getContributionDao().getContributionbyUser(u);
			
			// Set the user to null and update
			for(Contribution c : lc) {
			    c.setUser_fk(null);
			    DaoFactory.getContributionDao().update(c);
			}
			
			// Remove the category with the id
			DaoFactory.getUserDao().remove(id);
		}
		catch(UnknownException e) {
			
		}
		
		response.sendRedirect("/SupCrowdFunder/auth/admin/listUser");
	}
}
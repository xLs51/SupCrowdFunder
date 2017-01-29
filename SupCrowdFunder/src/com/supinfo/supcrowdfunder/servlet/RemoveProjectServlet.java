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

@WebServlet("/auth/admin/removeProject")
public class RemoveProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveProjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get the id
			Long id = Long.valueOf(request.getParameter("id"));
			
			// Get all the contribution of the project
			List<Contribution> lc = DaoFactory.getContributionDao().getContributionbyProject(id);
			System.out.println(lc);
			
			// Set the project and rewards to null and update
			for(Contribution c : lc) {
			    c.setProject_fk(null);
			    c.setRewards_fk(null);
			    DaoFactory.getContributionDao().update(c);
			}
			
			// Remove the project with the id
			DaoFactory.getProjectDao().remove(id);
		}
		catch(UnknownException e) {
			
		}
		
		response.sendRedirect("/SupCrowdFunder/auth/admin/listProject");
	}
}
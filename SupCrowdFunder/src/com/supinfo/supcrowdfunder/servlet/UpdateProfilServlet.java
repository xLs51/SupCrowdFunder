package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Contribution;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/updateProfil")
public class UpdateProfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UpdateProfilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the mail of the user in the session
		HttpSession session = ((HttpServletRequest)request).getSession();
		String mail = (String) session.getAttribute("username");
		
		// Find the user with the mail
		User u = DaoFactory.getUserDao().getUserByMail(mail);
		
		// Get all the projects from the user
		List<Project> lp = DaoFactory.getProjectDao().getProjectbyUser(u);
		// Create a variable with the number of the projects created by the user
		int nbProject = lp.size();
		
		// Get all the contributions from the user
		List<Contribution> lc = DaoFactory.getContributionDao().getContributionbyUser(u);
		// Create a variable with the number of the contributions done by the user
		int nbContribution = lc.size();
		
		// Set an attribute of all the projects
		request.setAttribute("listProject", lp);
		// Set an attribute of all the contributions
		request.setAttribute("listContribution", lc);
		// Set an attribute of the number of projects 
		request.setAttribute("nbProject", nbProject);
		// Set an attribute of the number of contributions 
		request.setAttribute("nbContribution", nbContribution);
		// Set an attribute of the user
		request.setAttribute("showUser", u);
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/updateProfil.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the parameters
		String mail = request.getParameter("mail");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");
		String address = request.getParameter("address");
		String password = request.getParameter("password");
		Long id = Long.valueOf(request.getParameter("id"));
		
		// Find the user with the id
		User u = DaoFactory.getUserDao().findById(id);
		
		u.setMail(mail);
		u.setLastName(lastName);
		u.setFirstName(firstName);
		u.setAddress(address);
		u.setPassword(password);
		
		// Update the user with the new parameters
		DaoFactory.getUserDao().update(u);
		
		// Wait 2 seconds before the redirection
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/SupCrowdFunder/auth/updateProfil");
	}
}
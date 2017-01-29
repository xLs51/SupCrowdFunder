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
import com.supinfo.supcrowdfunder.entity.Rewards;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/addContribution")
public class AddContribution extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public AddContribution()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		try {
			// Get the id of the project
			Long id = Long.valueOf(request.getParameter("id"));
			
			// If the id exist
			if(DaoFactory.getProjectDao().findById(id) != null) {
				// Find the project with the id
				Project p = DaoFactory.getProjectDao().findById(id);
				// Find all the rewards for this project
				List<Rewards> lr = DaoFactory.getRewardsDao().getRewardsbyProject(p);
				
				// Set an attribute of the project
				request.setAttribute("Project", p);
				// Set an attribute of all the rewards of this project
				request.setAttribute("listRewards", lr);
				
				RequestDispatcher rd = request.getRequestDispatcher("/auth/addContribution.jsp");
				rd.forward(request,response);
			}			
		} catch (Exception e) {
			response.sendRedirect("/SupCrowdFunder/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Get the parameters
		int amount = Integer.parseInt(request.getParameter("amount"));
		Long id_rewards = Long.valueOf(request.getParameter("rewards"));
		Long id_project = Long.valueOf(request.getParameter("idProject"));
		
		// Find the reward with the id
		Rewards r = DaoFactory.getRewardsDao().findById(id_rewards);
		
		// Get the mail of the user logged
		HttpSession session = ((HttpServletRequest)request).getSession();
		String mail = (String) session.getAttribute("username");
		
		// Find the user with the mail
		User u = DaoFactory.getUserDao().getUserByMail(mail);
		u.setNb_contribute(u.getNb_contribute() + 1);
		
		// Find the project with the id
		Project p = DaoFactory.getProjectDao().findById(id_project);
		p.setCurrentFund(p.getCurrentFund() + amount);
		p.setNb_contribution(p.getNb_contribution() + 1);
		
		// Create the contribution
		Contribution c = new Contribution();
		c.setPrice(amount);
		c.setRewards_fk(r);
		c.setUser_fk(u);
		c.setProject_fk(id_project);
		
		// Update the user
		DaoFactory.getUserDao().update(u);
		// Update the project
		DaoFactory.getProjectDao().update(p);
		// Add the contribution
		DaoFactory.getContributionDao().add(c);
	
		// Redirect
		response.sendRedirect("/SupCrowdFunder/showProject?id=" + id_project);
	}
}
package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Contribution;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/admin/viewData")
public class ViewDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewDataServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find all the users
		List<User> u = DaoFactory.getUserDao().findAll();
		int numberUser = u.size();
		
		// Find all the contributions
		List<Contribution> c = DaoFactory.getContributionDao().findAll();
		int numberContribution = c.size();
		
		// Find all the prices of all the contributions
		Long numberContributionPrice = DaoFactory.getContributionDao().getSumContributionPrice();
		
		// Find all the projects
		List<Project> p = DaoFactory.getProjectDao().findAll();
		int numberProject = p.size();
		
		// Set an attribute of the total of the number of users
		request.setAttribute("numberUser", numberUser);	
		// Set an attribute of the total of the number of contributions
		request.setAttribute("numberContribution", numberContribution);	
		// Set an attribute of the total of the number of contributions prices
		request.setAttribute("numberContributionPrice", numberContributionPrice);	
		// Set an attribute of the total of the number of projects
		request.setAttribute("numberProject", numberProject);			
	
		RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/viewData.jsp");
		rd.forward(request,response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
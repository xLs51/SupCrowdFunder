package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.Rewards;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/createProject")
public class CreateProjectServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public CreateProjectServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Create list of categories
		List<Category> lc = new ArrayList<Category>();
		
		// If no category available, create 2
		if(DaoFactory.getCategoryDao().findAll().isEmpty()) {
			// Create the categories
			Category c = new Category();
			c.setDescription("Here is the fantastic category which is about everything about geek-passions");
			c.setName("Geek");
			
			Category c1 = new Category();
			c1.setDescription("Every projects about music domain");
			c1.setName("Music");
			
			// Add the categories
			DaoFactory.getCategoryDao().add(c);	
			DaoFactory.getCategoryDao().add(c1);	
			
			lc.add(c);
			lc.add(c1);
		}
		// Else find all the categories
		else
			lc = DaoFactory.getCategoryDao().findAll();
		
		// Set an attribute of all the categories
		request.setAttribute("listCategory", lc);
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/createProject.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Get the parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		int goal = Integer.parseInt(request.getParameter("goal"));
		Long category = Long.valueOf(request.getParameter("category"));
		String rewardsName = request.getParameter("rewardsName");
		String rewardsDescription = request.getParameter("rewardsDescription");
		int rewardsPriceMin = Integer.parseInt(request.getParameter("rewardsPriceMin"));
		
		// Get the mail of the user logged
		HttpSession session = ((HttpServletRequest)request).getSession();
		String mail = (String) session.getAttribute("username");
		
		// Find the user with the mail
		User u = DaoFactory.getUserDao().getUserByMail(mail);
		
		// Find the category with the id
		Category c = DaoFactory.getCategoryDao().findById(category);
		
		// Create the project
		Project p = new Project();
		p.setName(name);
		p.setDescription(description);
		p.setGoal(goal);
		p.setCategory_fk(c);
		p.setUser_fk(u);
		
		// Create the rewards
		Rewards r = new Rewards();
		r.setName(rewardsName);
		r.setDescription(rewardsDescription);
		r.setPrice_min(rewardsPriceMin);
		r.setProject(p);
		
		// Formatter to transform the String date into a Date Format
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		
		// Try the formatting
		try {
			Date date_start = formatter.parse(start_date);
			p.setStart_date(date_start);
			
			Date date_end = formatter.parse(end_date);
			p.setEnd_date(date_end);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// Add the project
		DaoFactory.getProjectDao().add(p);
		// Add the reward
		DaoFactory.getRewardsDao().add(r);
		
		// Redirect
		response.sendRedirect("/SupCrowdFunder/auth/createProject");
	}
}
package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.UnknownException;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/admin/updateProject")
public class UpdateProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateProjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get parameters
			Long id = Long.valueOf(request.getParameter("id"));
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			int goal = Integer.parseInt(request.getParameter("goal"));
			int currentFund = Integer.parseInt(request.getParameter("currentFund"));
			int contribution = Integer.parseInt(request.getParameter("contribution"));
			String start_date = request.getParameter("start_date");
			String end_date = request.getParameter("end_date");
			Long id_user = Long.valueOf(request.getParameter("creator"));
			
			// Find the user with the id
			User u = DaoFactory.getUserDao().findById(id_user);

			Project p = DaoFactory.getProjectDao().findById(id);			
			p.setName(name);
			p.setDescription(description);
			p.setGoal(goal);
			p.setCurrentFund(currentFund);
			p.setNb_contribution(contribution);
			p.setUser_fk(u);
			
			// Formater to transorm the String date into a Date Format
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
			
			// Update the project
			DaoFactory.getProjectDao().update(p);
		}
		catch(UnknownException e) {
			
		}
		
		// Wait 2 seconds before the redirection
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/SupCrowdFunder/auth/admin/listProject");
	}
}
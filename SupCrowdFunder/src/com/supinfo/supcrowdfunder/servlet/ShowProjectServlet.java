package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/showProject")
public class ShowProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowProjectServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		try {
			// Get the id of the project
			Long id = Long.valueOf(request.getParameter("id"));
			
			// If the id doesn't exist
			if(DaoFactory.getProjectDao().findById(id) == null) {
				PrintWriter out = response.getWriter();
				out.println("Project with id " + id + " not found !");
			}
			else {
				// Find the project with the id
				Project p = DaoFactory.getProjectDao().findById(id);

				// Set an attribute of the project
				request.setAttribute("showProject", p);
				
				String mail = "";
				HttpSession session = ((HttpServletRequest)request).getSession();
				
				// If the user is logged
				if(session.getAttribute("username") != null)
					mail = (String) session.getAttribute("username");
				
				try {
					// Find the user with the mail
					User u = DaoFactory.getUserDao().getUserByMail(mail);
					// Get all the contribution of this user on this project
					List<Contribution> lc = DaoFactory.getContributionDao().getContributionbyUserAndProject(u,id);
					// If the list is not empty
					if(!lc.isEmpty()) {
						// Set an attribute of all the contributions
						request.setAttribute("listContribution", lc);
					}
				} catch (Exception e) {
					
				}

				RequestDispatcher rd = request.getRequestDispatcher("/showProject.jsp");
				rd.forward(request,response);			
			}
		} catch (Exception e) {
			response.sendRedirect("/SupCrowdFunder/");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
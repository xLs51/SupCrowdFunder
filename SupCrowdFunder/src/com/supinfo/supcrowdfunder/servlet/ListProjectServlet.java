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
import com.supinfo.supcrowdfunder.entity.Project;

@WebServlet("/auth/admin/listProject")
public class ListProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ListProjectServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find all the projects
		List<Project> lp = DaoFactory.getProjectDao().findAll();
		
		// Set an attribute of all the projects
		request.setAttribute("listProject", lp);
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/listProject.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}
}
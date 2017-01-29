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
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;

@WebServlet("/projectByCategory")
public class ShowProjectByCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShowProjectByCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Find all the category
		List<Category> lc = DaoFactory.getCategoryDao().findAll();
		// Set an attribute with the list of all the categories
		request.setAttribute("listCategory", lc);
		
		RequestDispatcher rd = request.getRequestDispatcher("/projectByCategory.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the id of the category
		Long id = Long.valueOf(request.getParameter("category"));
		// Find the category with the id
		Category c = DaoFactory.getCategoryDao().findById(id);
		// Get all the projects that have the category found
		List<Project> lp = DaoFactory.getProjectDao().getProjectByCategory(c);		
		// Find all the category to the select without the doGet method
		List<Category> lc = DaoFactory.getCategoryDao().findAll();
		
		// Set an attribute with the list of all the categories
		request.setAttribute("listCategory", lc);
		// Set an attribute with the list of all the project that contain the category found
		request.setAttribute("listProject", lp);
		// Set an attribute with id of the chosen category
		request.setAttribute("Category", c);
		
		RequestDispatcher rd = request.getRequestDispatcher("/projectByCategory.jsp");
		rd.forward(request,response);
	}
}
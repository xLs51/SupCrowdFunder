package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.dao.DaoFactory;

@WebServlet("/auth/admin/addCategory")
public class AddCategoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    public AddCategoryServlet()
    {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		RequestDispatcher rd = request.getRequestDispatcher("/auth/addCategory.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// Get the parameters
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		// Create the category
		Category c = new Category();
		c.setName(name);
		c.setDescription(description);
		
		// Add the category
		DaoFactory.getCategoryDao().add(c);
		
		// Wait 2 seconds before the redirection
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// Redirect
		response.sendRedirect("/SupCrowdFunder/auth/admin/listCategory");
	}
}
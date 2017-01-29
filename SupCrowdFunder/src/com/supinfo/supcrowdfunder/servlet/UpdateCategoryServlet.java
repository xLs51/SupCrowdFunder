package com.supinfo.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.portable.UnknownException;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;

@WebServlet("/auth/admin/updateCategory")
public class UpdateCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateCategoryServlet() {
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
			
			// Find the category with the id
			Category c = DaoFactory.getCategoryDao().findById(id);
			c.setName(name);
			c.setDescription(description);
			
			// Wait 2 seconds before the redirection
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Update the category
			DaoFactory.getCategoryDao().update(c);
		}
		catch(UnknownException e) {
			
		}
		
		response.sendRedirect("/SupCrowdFunder/auth/admin/listCategory");
	}
}
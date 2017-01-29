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

import org.omg.CORBA.portable.UnknownException;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;

@WebServlet("/auth/admin/removeCategory")
public class RemoveCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveCategoryServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// Get the id
			Long id = Long.valueOf(request.getParameter("id"));
			
			// Find the category with the id
			Category c = DaoFactory.getCategoryDao().findById(id);
			// Get all the projects that have the category found
			List<Project> lp = DaoFactory.getProjectDao().getProjectByCategory(c);
			
			if(!lp.isEmpty()) {
				PrintWriter out = response.getWriter();
				out.println("Category : " + c.getName() + " cannot be deleted");
				out.println("This category is associated to a project !");
				
				// Set an attribute with the error
				request.setAttribute("category", "error");
				
				RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/listCategory.jsp");
				rd.forward(request,response);
			}
			else {
				// Remove the category with the id
				DaoFactory.getCategoryDao().remove(id);
				response.sendRedirect("/SupCrowdFunder/auth/admin/listCategory");
			}
		}
		catch(UnknownException e) {
			
		}
	}
}
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
import com.supinfo.supcrowdfunder.entity.User;

@WebServlet("/auth/admin/listUser")
public class ListUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ListUserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Finnd all the users
		List<User> lu = DaoFactory.getUserDao().findAll();
	
		// Set an attribute with all the users
		request.setAttribute("listUser", lu);
		
		// Get the id of the current User
		HttpSession session = ((HttpServletRequest)request).getSession();
		String mail = (String) session.getAttribute("username");
		Long idUser = DaoFactory.getUserDao().getUserByMail(mail).getId();
		
		// Set an attribute with all the users
		request.setAttribute("idUser", idUser);
		
		RequestDispatcher rd = request.getRequestDispatcher("/auth/admin/listUser.jsp");
		rd.forward(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
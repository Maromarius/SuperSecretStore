package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.dao.OrderDAO;
import com.gamestore.dao.UnitofWork;

/**
 * Servlet implementation class CommitOrderChangesServlet
 */
@WebServlet("/CommitOrderChangesServlet")
public class CommitOrderChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitOrderChangesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		UnitofWork.getCurrent().commit();
		session.setAttribute("allOrders", OrderDAO.getInstance().getAll());
		response.sendRedirect("ManageOrders.jsp");
		
	}

}

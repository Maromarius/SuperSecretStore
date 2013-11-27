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
import com.gamestore.model.Order;

/**
 * Servlet implementation class DeleteOrderServlet
 */
@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderIDtoDelete = Integer.parseInt(request.getParameter("OrderIDtoDelete").toString());
		Order order = OrderDAO.getInstance().get(orderIDtoDelete);
		order.markRemoved();
		response.sendRedirect("ManageOrders.jsp");
	}

}

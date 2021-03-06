 package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.controller.PaymentServices;
import com.gamestore.dao.UnitofWork;
import com.gamestore.model.Order;
import com.gamestore.model.ShoppingCart;
import com.gamestore.model.User;

/**
 * Servlet implementation class MakeOrderServlet
 */
@WebServlet("/MakeOrderServlet")
public class MakeOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeOrderServlet() {
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
		PaymentServices ps = new PaymentServices(request.getParameter("userCreditCardNumber"));
		User user = (User) session.getAttribute("user");
		if(ps.VerifyPayment())
		{
			ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
			Order order = new Order(1, cart.makeOrder(), "Pending", user);
			order.markNew();
			UnitofWork.getCurrent().commit();
			session.setAttribute("ShoppingCart", null);
			session.setAttribute("OrderDetailsMessage", "Order Complete! Thanks");
		}
		else
		{
			session.setAttribute("OrderDetailsMessage", "Payment Not Accepted, your shopping cart is still there though...");
		}
		response.sendRedirect("OrderCompletedDetails.jsp");
	}

}

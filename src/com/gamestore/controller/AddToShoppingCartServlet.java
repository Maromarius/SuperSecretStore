package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.model.ItemContainer;
import com.gamestore.model.ShoppingCart;

/**
 * Servlet implementation class AddToShoppingCartServlet
 */
@WebServlet("/AddToShoppingCartServlet")
public class AddToShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToShoppingCartServlet() {
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
		int itemID = Integer.parseInt(request.getParameter("itemToAddID"));
		
		if(session.getAttribute("ShoppingCart") == null)
		{
			session.setAttribute("ShoppingCart", new ShoppingCart());
		}
		
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		cart.Add(ItemContainer.getInstance().Get(itemID));
		session.setAttribute("ShoppingCart", cart);
		response.sendRedirect("ItemListViewer.jsp");
		
	}

}

package com.gamestore.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.model.Item;
import com.gamestore.model.ShoppingCart;

/**
 * Servlet implementation class UpdateShoppingCartServlet
 */
@WebServlet("/UpdateShoppingCartServlet")
public class UpdateShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateShoppingCartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
		if(cart != null)
		{
			ArrayList<Item> items = cart.getSetList();
			for(int i = 0; i < items.size(); i++)
			{
				String strCheckBoxValue = request.getParameter(items.get(i).getID()+"CheckBox");
				int itemQuantity = Integer.parseInt(request.getParameter((items.get(i).getID()+"Quantity")));
				
				if (strCheckBoxValue != null)  
				{
				   	cart.Remove(items.get(i), cart.getCount(items.get(i).getID()));
				}  
				else  
				{
				    if(itemQuantity >= 0 && cart.getCount(items.get(i).getID()) != itemQuantity)
				    {
				    	cart.setQuantity(items.get(i),itemQuantity);
				    }
				}
			}
			session.setAttribute("ShoppingCart", cart);
			response.sendRedirect("ShoppingCart.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

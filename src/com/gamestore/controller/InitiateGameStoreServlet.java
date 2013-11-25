package com.gamestore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.dao.ItemDAO;
import com.gamestore.model.Item;
import com.gamestore.model.ItemContainer;

/**
 * Servlet implementation class InitiateGameStoreServlet
 */
@WebServlet("/InitiateGameStoreServlet")
public class InitiateGameStoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitiateGameStoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		if(session.getAttribute("ShoppingCart") != null)
		{
			//create new shopping cart
			session.setAttribute("ShoppingCart", null);
		}
		
		ItemContainer.getInstance().Reset();
		//Get Items from db and load them to model.
		HashMap<Integer,Item> itemMap = ItemDAO.getInstance().findAll();
		Iterator entries = itemMap.entrySet().iterator();
		while (entries.hasNext()) {
		    Map.Entry entry = (Map.Entry) entries.next();
		    Item item = (Item) entry.getValue();
		    ItemContainer.getInstance().Add(item);
		}
		//Loading items complete
		response.sendRedirect("HomePage.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

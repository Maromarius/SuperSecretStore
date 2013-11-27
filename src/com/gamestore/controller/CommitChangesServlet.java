package com.gamestore.controller;

import java.io.IOException;
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
import com.gamestore.dao.ItemUnitofWork;
import com.gamestore.model.Item;
import com.gamestore.model.ItemContainer;

/**
 * Servlet implementation class CommitChangesServlet
 */
@WebServlet("/CommitChangesServlet")
public class CommitChangesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommitChangesServlet() {
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		
		//Traverse the UOW for dirty objects and commit changes to the db.
		ItemUnitofWork.getInstance().commit();
		
		//Retrieve back all items from the database and populate ItemContainer.
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
		response.sendRedirect("ManageInventory.jsp");
		
	}

}

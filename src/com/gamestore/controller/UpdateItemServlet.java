package com.gamestore.controller;

import java.io.IOException;

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
 * Servlet implementation class UpdateItemServlet
 */
@WebServlet("/UpdateItemServlet")
public class UpdateItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ItemContainer.getInstance().update(itemIdToUpdate);
		int itemIdToUpdate = Integer.parseInt(request.getParameter("itemID"));
		Item i = ItemContainer.getInstance().Get(itemIdToUpdate);
		request.getSession(true).setAttribute("itemToUpdate", i);
		response.sendRedirect("UpdateInventoryItem.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Item item = (Item) request.getSession().getAttribute("itemToUpdate");
		item.setName(request.getParameter("itemName"));
		item.setDescription(request.getParameter("itemDescription"));
		item.setImgUrl(request.getParameter("itemImageURL"));
		item.setPrice(Double.parseDouble(request.getParameter("itemPrice")));
		item.setQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
		item.setPlatform(request.getParameter("itemPlatform"));
		item.setType(request.getParameter("itemType"));
		ItemContainer.getInstance().Update(item);
		
		//obsolete, we'll do this in the commit button section.
		//ItemDAO.getInstance().update(item.getID(), item.getName(), item.getPrice(), item.getQuantity(), item.getDescription(), item.getImgUrl(), item.getPlatform(), item.getType());
		request.getSession(true).setAttribute("itemToUpdate", item);
		//item = null;
		response.sendRedirect("ManageInventory.jsp");
	}

}

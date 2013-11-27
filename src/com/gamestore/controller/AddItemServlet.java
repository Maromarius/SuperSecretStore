package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.dao.ItemDAO;
import com.gamestore.dao.ItemUnitofWork;
import com.gamestore.model.Item;
import com.gamestore.model.ItemContainer;

/**
 * Servlet implementation class AddItemServlet
 */
@WebServlet("/AddItemServlet")
public class AddItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Item item = new Item();
		
		item.setName(request.getParameter("itemName"));
		item.setDescription(request.getParameter("itemDescription"));
		item.setImgUrl(request.getParameter("itemImageURL"));
		item.setPrice(Double.parseDouble(request.getParameter("itemPrice")));
		item.setQuantity(Integer.parseInt(request.getParameter("itemQuantity")));
		item.setPlatform(request.getParameter("itemPlatform"));
		item.setType(request.getParameter("itemType"));
		ItemContainer.getInstance().Add(item);
		
		//Obselete, UOW will take care upon commit
		ItemUnitofWork.getInstance().registerNew(item);
		//ItemDAO.getInstance().addbyObject(item);
		item =null;
		response.sendRedirect("ManageInventory.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}

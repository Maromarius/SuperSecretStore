package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gamestore.dao.ItemDAO;
import com.gamestore.dao.ItemUnitofWork;
import com.gamestore.model.ItemContainer;

/**
 * Servlet implementation class DeleteItemFromInventoryServlet
 */
@WebServlet("/DeleteItemFromInventoryServlet")
public class DeleteItemFromInventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteItemFromInventoryServlet() {
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
		HttpSession session = request.getSession(true);
		
		int itemIdToDelete = Integer.parseInt(request.getParameter("ItemIdToDelete"));
		ItemUnitofWork.getInstance().registerRemoved(ItemContainer.getInstance().Get(itemIdToDelete));
		ItemContainer.getInstance().Remove(itemIdToDelete);
		//obsolete, will get done in the CommitChangesServlet with UOW
		//ItemDAO.getInstance().delete(itemIdToDelete);
		response.sendRedirect("ManageInventory.jsp");
	}

}

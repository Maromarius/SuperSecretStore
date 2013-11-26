package com.gamestore.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gamestore.model.User;
import com.gamestore.util.DatabaseConnection;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() 
    {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		DatabaseConnection.getInstance();
		String email = request.getParameter("username");
		String password = request.getParameter("password");
		RequestDispatcher dispatcher;
		if (email.isEmpty() || password.isEmpty())
		{
			request.getSession(true).setAttribute("error", "email or Password are empty");
			dispatcher=request.getRequestDispatcher("HomePage.jsp");
		}
		else{
			User user = LoginGateway.getInstance().login(email, password);
			boolean isAdmin = false;
			if(user!=null){
				
				if(user.isAdmin() == 1){
					isAdmin = true;
				}
				request.getSession(true).setAttribute("isAdmin", isAdmin);
				request.getSession(true).setAttribute("user", user);
				request.getSession(true).setAttribute("username", email);
				ServletContext context = this.getServletContext();
				dispatcher = context.getRequestDispatcher("/InitiateGameStoreServlet");
				dispatcher.forward(request, response);
		
			}
			else
			{
				request.getSession(true).setAttribute("error", "Invalid Login Info");
				dispatcher=request.getRequestDispatcher("index.jsp");
			}
			
			
		}
		/*
		//this is temporary
		if (true)
		{
			//User authentication succeeded
			request.getSession().setAttribute("username", email);
			ServletContext context = this.getServletContext();
			dispatcher = context.getRequestDispatcher("/InitiateGameStoreServlet");
			dispatcher.forward(request, response);
			return;
		}
		else
		{
			//User authentication failed
			request.getSession().setAttribute("msg", new String("Invalid username &/or password."));
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
			return;
		}
		*/
	}

}

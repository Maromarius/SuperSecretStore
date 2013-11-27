<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gamestore.model.Item"%>
<%@page import="com.gamestore.model.User"%>
<%@page import="com.gamestore.model.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.text.NumberFormat"%>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>GameStore</title>
	<meta name="Robots" content="index,follow" />
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery.tools.js"></script>
	<script type="text/javascript" src="js/cufon-yui.js"></script>
	<script type="text/javascript" src="js/Titillium.font.js"></script>
	<script type="text/javascript">
		Cufon.replace('h1,h2,h3,#menu li a');
	</script>
	<script type="text/javascript" charset="utf-8">
		$(document).ready(function() { anim(); $(".scrollable").scrollable({ }); });
	</script>
	<script type="text/javascript" src="js/jquery.fancybox-1.3.2.pack.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$(".items a").fancybox({
				'titlePosition'		: 'inside',
				'overlayColor'		: '#000',
				'overlayOpacity'	: 0.9
			});
		});
	</script>
	<script type="text/javascript" src="js/pitch.js"></script>
	<script type="text/javascript">
		/* 
		
		COUNTDOWN
		-------------------------------------------------------------------
		replace UTC with your time zone (eg. UTC+01 for Paris)
		list of time zone codes: http://en.wikipedia.org/wiki/Time_zone#UTC
		
		*/
		Counter(new Date("Oct 20 2012 12:00:00 UTC"));
		var done_message = "Game On!";
	</script>
	<link rel="stylesheet" type="text/css" href="css/main.css" media="screen" />
	<link rel="stylesheet" href="images/fancybox/jquery.fancybox-1.3.2.css" type="text/css" media="screen" />
</head>
<body>
	<div id="content">
	
		<!-- top -->
		<div id="top">
			<h1 id="logo"><a href="HomePage.jsp">Game<span>Store</span></a></h1>
			<ul id="menu">
				<% 
				Boolean isAdmin = (Boolean) (session.getAttribute("isAdmin"));
				if(isAdmin){
					out.println("<li><a href=" + "ManageOrders.jsp" + ">Manage Orders</a></li>");
					out.println("<li><a href=" + "ManageInventory.jsp" + ">Manage Inventory</a></li>");
				}
			%>
				<li><a href="HomePage.jsp">Home</a></li>
				<li><a href="ItemListViewer.jsp">Products</a></li>
				<li><a class="current" href="#">Shopping Cart <%
				if(session.getAttribute("ShoppingCart") != null)
				{
					ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
					if(cart.getList().size()>0)
						out.print(" ("+cart.getList().size()+")");
				}
				%></a></li>
				<li><a href="ManageUserOrders.jsp">My Order</a></li>
			</ul>
		</div>
		<!-- /top -->
		
		<!-- pitch -->
		<div id="pitch">
			<h2 id="hello">That's it, you're done!</h2>
		</div>
		<!-- /pitch -->
		
		<%
			    User user = (User) session.getAttribute("user");
				String message = (String) session.getAttribute("OrderDetailsMessage");
				String displayMessage = message+" "+user.getName();
					%>
				<center>
					<h3><%=displayMessage.toString()%></h3>
					Come back anytime!
					<form action="ItemListViewer.jsp" method="post">
						<input class="addtocartbutton" type="submit" onclick="ItemListViewer.jsp" value="Back to Store" />
					</form>
					<p></p>
					<p></p>
					<p></p>
					<p></p>
				</center>
				
		<!-- footer -->
		<div id="footer">
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot; </p>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
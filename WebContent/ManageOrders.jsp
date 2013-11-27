<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gamestore.model.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
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
				<li><a class="current" href="#">Manage Order</a></li>
				<li><a href="ManageInventory.jsp">Manage Inventory</a></li>
				<li><a href="HomePage.jsp">Home</a></li>
				<li><a href="ItemListViewer.jsp">Products</a></li>
				<li><a href="ShoppingCart.jsp">Shopping Cart</a></li>
				<li><a href="ManageUserOrders.jsp">My Order</a></li>
			</ul>
		</div>
		<!-- /top -->
		
		<!-- pitch -->
		<div id="pitch">
			<h2 id="hello">Orders = Money</h2>
		</div>
		<!-- /pitch -->
		
		
		<div id="items">
		
		<table>
			<tr>
			
				<th width="80" align="left">Order ID</th>
				<th width="500" align="left">User ID</th>
				<th width="80" align="left">Total</th>
				<th width="100" align="left">Status</th>
				<th width="80" align="left" colspan="2" align="center">Delete / Update</th>
				<th></th>
			</tr>
			
			<%
				ArrayList<Order> orders = (ArrayList<Order>) session.getAttribute("allOrders");
				if(orders != null)
				{
					for(int i = 0 ; i < orders.size(); i++) {
				    	Order order = orders.get(i);
				    %>
				 
				    <tr>
						<td align="left"><%=order.getID()%></td>
						<td><%=order.getUserID() %></td>
						<td><%=order.getTotal() %></td>
						<td>$<%=order.getStatus() %></td>
						<td>
							<form action="DeleteOrderServlet" method="post">
								<input type="submit" value="Delete">
								<input type="hidden" name=OrderIDtoDelete value=<%=order.getID()%>>
							</form>
							</td>
						<td></td>
					</tr>
			
			<%
					}
				}
			%>
			
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><form action="CommitOrderChangesServlet" method="POST">
							<input class="addtocartbutton" type="submit" value="Commit" />
						</form></td>
				</tr>
			
		</table>
		
		</div>
		
		<!-- footer -->
		<div id="footer">
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot; </p>
		</div>
		
		<!-- /footer -->
	</div>
</body>
</html>
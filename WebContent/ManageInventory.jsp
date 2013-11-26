<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.gamestore.model.Item"%>
<%@page import="com.gamestore.model.ItemContainer"%>
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
				<li><a href="ManageOrders.jsp">Manage Orders</a></li>
				<li><a class="current" href="#">Manage Inventory</a></li>
				<li><a href="HomePage.jsp">Home</a></li>
				<li><a href="ItemListViewer.jsp">Products</a></li>
				<li><a href="ShoppingCart.jsp">Shopping Cart</a></li>
			</ul>
		</div>
		<!-- /top -->
		
		<!-- pitch -->
		<div id="pitch">
			<h2 id="hello">The Inventory YOU care about</h2>
		</div>
		<!-- /pitch -->
		
		
		
		<div id="items">
		
		<table>
			<tr>
			
				<th width="80" align="left">Item ID</th>
				<th width="500" align="left">Item Description</th>
				<th width="80" align="left">Quantity</th>
				<th width="100" align="left">Price/Unit</th>
				<th width="80" align="left" colspan="2" align="center">Edit</th>
				<th></th>
			</tr>
			
			<% 
			ArrayList<Item> itemsToDisplay = new ArrayList<Item>();
			itemsToDisplay = ItemContainer.getInstance().GetAllItems();
			for(Iterator<Item> i = itemsToDisplay.iterator(); i.hasNext();) {
			    Item item = i.next();
			%>
				 
				    <tr>
						<td align="left"><%=item.getID() %></td>
						<td><%=item.getName() %> (<%=item.getDescription() %>)</td>
						<td><%=item.getQuantity() %></td>
						<td>$<%=item.getPrice() %></td>
						<td>
							<form action="DeleteItemFromInventoryServlet" method="post">	
								<input type="submit" value="Delete">
								<input type="hidden" name=ItemIdToDelete value=<%=item.getID()%>>
							</form>
							</td>
						<td>
							<form action="" method="post">	
								<input type="submit" value="Update">
								<input type="hidden" name="itemID" value=<%=item.getID()%>>
							</form>
						</td>
					</tr>
			
			<%	
				}
			%>
			
				<tr>
					<td><form action="AddInventory.jsp" method="POST">
							<input class="addtocartbutton" type="submit" value="Add Item" />
						</form></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><form action="CommitInventory" method="POST">
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
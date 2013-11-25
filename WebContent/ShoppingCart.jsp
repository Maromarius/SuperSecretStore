<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gamestore.model.Item"%>
<%@page import="com.gamestore.model.ShoppingCart"%>
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
			<h1 id="logo"><a href="#">Game<span>Store</span></a></h1>
			<ul id="menu">
				<li h><a href="#">Manage Inventory</a></li>
				<li><a href="#">Home</a></li>
				<li><a href="#">Products</a></li>
				<li><a class="current" href="#">Shopping Cart</a></li>
			</ul>
		</div>
		<!-- /top -->
		
		<!-- pitch -->
		<div id="pitch">
			<h2 id="hello">One Shopping Cart to Rule Them All</h2>
		</div>
		<!-- /pitch -->
		
		
		<table>
			<tr  height="30">
				<th width="30" align="left">Del</th>
				<th width="500" align="left">Item Description</th>
				<th width="80" align="left">In Stock</th>
				<th width="80" align="left">Part #</th>
				<th width="15" align="left">QTY</th>
				<th width="100" align="left">Price</th>
			</tr>
			
			<%
				ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
				for(Iterator<Item> i = cart.getList().iterator(); i.hasNext(); ) {
			    	Item item = i.next();
			    %>
			    <tr>
				<td align="left"><input type="checkbox"></td>
				<td><%=item.getName()%> (<%=item.getPlatformName()%>)</td>
				<td><%=item.getStockStatus() %></td>
				<td><%=item.getID()%></td>
				<td>
					<input type="text" name="gamequantity" value="<%=item.getID()%>" type="hidden">
					<input type="text" name="gamequantitytextbox" value="<%=item.getID()%>" width="10">
				</td>
				<td>$<%=item.getPrice()%></td>
			</tr>
			<%
				}
			%>
			
			<tr>
				<td align="left"><input type="checkbox"></td>
				<td>Mass Effect 2  (XBOX 360)</td>
				<td>In Stock</td>
				<td>51645</td>
				<td><input type="text" width="10"></td>
				<td>$19.99</td>
			</tr>
			<tr>
				<td align="left"><input type="checkbox"></td>
				<td>Mass Effect 2  (XBOX 360)</td>
				<td>In Stock</td>
				<td>51645</td>
				<td><input type="text" width="10"></td>
				<td>$19.99</td>
			</tr>
			<tr >
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td align="center">Total</td>
				<td>$38.98</td>
			</tr>
			<tr>
				<td><a class="more" href="UpdateCart">Update</a></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a class="more" href="CheckoutCart">Checkout</a></td>
			</tr>
		
		</table>
			
		
		
		
		
		
		
		
		<!-- footer -->
		<div id="footer">
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot; </p>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
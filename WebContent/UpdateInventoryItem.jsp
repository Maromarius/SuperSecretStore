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
				<li><a class="current" href="ManageInventory.jsp">Manage Inventory</a></li>
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
	
		<!-- columns -->
		<div id="cols">
			<!-- left column -->
			<div class="col">
				<h3></h3>
				<p></p>
				<p></p>
			</div>
			<!-- /left column -->
			
			<!-- middle column -->
			<div class="col">
				<h3>Update Item</h3>
		<%
        	Item item = (Item) session.getAttribute("itemToUpdate");
        %>
        <form action="UpdateItemServlet" method="post">
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="itemName" value=<% out.print(item.getName());%> ></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="itemPrice" value=<% out.print(item.getPrice());%> ></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="itemQuantity" value=<% out.print(item.getQuantity());%> ></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="itemDescription" value=<% out.print(item.getDescription());%> ></td>
            </tr>
            <tr>
                <td>Image URL</td>
                <td><input type="text" name="itemImageURL" value=<% out.print(item.getImgUrl());%> ></td>
            </tr>
            <tr>
                <td>Platform</td>
                <td><select id="sel" name="itemPlatform" >  
                <% String platformOptions[] = {"XBox", "XBox 360", "XBox One", "PC", "Playstation", "Playstation 2", "Playstation 3", "Playstation 4", "Wii", "Wii U", "Gamecube"};        
                for (String s : platformOptions)		
                	out.print("<option value=\"" + s +"\" "+ (s.equals(item.getPlatform())?"selected":"") +">"+ s +"</option>");
				%>		
					</select>
				</td>
            </tr>
            <tr>
                <td>Type :</td>
                <td><select name="itemType" >
		        <% String typeOptions[] = {"Game", "Console", "Accessory"};        
                	for (String s : typeOptions)		
                		out.print("<option value=\"" + s +"\" "+ (s.equals(item.getPlatform())?"selected":"") +">"+ s +"</option>");
				%>	
                </select></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input  type="submit" value="Update Item">             
                </td>
            </tr>
        </table>
        </form>
			</div>
			<!-- /middle column -->
			<!-- left column -->
			<div class="col">
				<h3></h3>
				<p></p>
				<p></p>
			</div>
			<!-- /left column -->
			
			<div class="clear"></div>
		</div>
		<!-- /columns -->	
		
		<!-- footer -->
		<div id="footer">
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot; </p>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
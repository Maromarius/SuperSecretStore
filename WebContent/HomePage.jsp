<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.gamestore.model.ShoppingCart"%>
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
				<li><a href="ManageInventory.jsp">Manage Inventory</a></li>
				<li><a class="current" href="HomePage.jsp">Home</a></li>
				<li><a href="ItemListViewer.jsp">Products</a></li>
				<li><a href="ShoppingCart.jsp">Shopping Cart <%
				if(session.getAttribute("ShoppingCart") != null)
				{
					ShoppingCart cart = (ShoppingCart) session.getAttribute("ShoppingCart");
					if(cart.getList().size()>0)
						out.print(" ("+cart.getList().size()+")");
				}
				%></a></li>
			</ul>
		</div>
		<!-- /top -->
		
		<!-- pitch -->
		<div id="pitch">
			<h2 id="hello">Revolutionizing the way you buy games online.</h2>
			<p id="countdown"></p>
		</div>
		<!-- /pitch -->
		
		<!-- screenshots -->		
		<div id="screenshots">		
			<a href="javascript:;" class="arrows prev">Previous</a>
		
			<div class="scrollable">
				<div class="items">
					
					<!-- group 1 -->
					<ul>
						<li><a href="http://compass.xboxlive.com/assets/f6/1f/f61f0b46-c571-4552-a5e1-61d227c57949.png?n=XboxOne_Console_Sensor_cont.png" title="Xbox One"><img src="http://compass.xboxlive.com/assets/f6/1f/f61f0b46-c571-4552-a5e1-61d227c57949.png?n=XboxOne_Console_Sensor_cont.png" width="160" height="105" alt="Image" /></a></li>
						<li><a href="http://g-ecx.images-amazon.com/images/G/01/aplus/detail-page/PS4_lg.jpg" title="Playstation 4"><img src="http://webassetsf.scea.com/pscomauth/groups/public/documents/webasset/ps4_play_img.jpg" width="160" height="90" alt="Image" /></a></li>
						<li><a href="http://www.wired.com/geekdad/wp-content/uploads/2012/11/wii-u.png" title="Wii U"><img src="http://www.wired.com/geekdad/wp-content/uploads/2012/11/wii-u.png" width="160" height="90" alt="Image" /></a></li>
						<li><a href="http://i.imgur.com/uyCL5XL.png" title="PC - Master Race"><img src="http://i.imgur.com/uyCL5XL.png" width="160" height="90"alt="Image" /></a></li>
					</ul>
					<!-- /group 1 -->	
				
					<!-- group 2 -->
					<ul>
						<li><a href="images/screenshots/big1.jpg" title="Image Caption"><img src="images/screenshots/thumb1.jpg" alt="Image" /></a></li>
						<li><a href="images/screenshots/big1.jpg" title="Image Caption"><img src="images/screenshots/thumb1.jpg" alt="Image" /></a></li>
						<li><a href="images/screenshots/big1.jpg" title="Image Caption"><img src="images/screenshots/thumb1.jpg" alt="Image" /></a></li>
						<li><a href="images/screenshots/big1.jpg" title="Image Caption"><img src="images/screenshots/thumb1.jpg" alt="Image" /></a></li>
					</ul>
					<!-- /group 2 -->
					
				</div>
			</div>
		
			<a href="javascript:;" class="arrows next">Next</a>
			
			<div class="clear"></div>
		</div>
		<!-- /screenshots -->
		
		<!-- columns -->
		<div id="cols">
			
			<!-- left column -->
			<div class="col">
				<h3>Lots of Games</h3>
				<p>The biggest video game collection ever is brought to you by GameStore. Browse all the games now!</p>
				<p><a class="more" href="#">Buy Games!</a></p>
			</div>
			<!-- /left column -->
			
			<!-- middle column -->
			<div class="col">
				<h3>Simple to Buy</h3>
				<p>Just enter your credit card number and we won't even charge you. That's right, it's free!</p>
				<br/>
			</div>
			<!-- /middle column -->
			
			<!-- right column -->
			<div class="col last">
				<h3>Latest and Greatest</h3>
				<p>We hold only the latest and greatest of platforms and their games, including Xbox One and Xbox 360.</p>
				<p><a class="more" href="#">Buy Consoles!</a></p>
			</div>
			<!-- /right column -->
			
			<div class="clear"></div>
		</div>
		<!-- /columns -->		
		
		<!-- main (left side) -->		
		<div id="main">
			<h3>Our Products</h3>
			<p><strong>To accompany your gaming collection, we offer a wide variety of console accessories.</strong></p>
			
			<ul>
				<li><a class="current" href="#">All Games</a></li>
				<li><a href="#">Accessories</a></li>
				<li><a href="#">Nintendo 3DS</a></li>
				<li><a href="#">Playstation</a></li>
				<li><a href="#">Playstation 2</a></li>
				<li><a href="#">Playstation 3</a></li>
				<li><a href="#">Playstation 4</a></li>
				<li><a href="#">PC</a></li>
				<li><a href="#">Wii</a></li>
				<li><a href="#">Wii U</a></li>
				<li><a href="#">Xbox</a></li>
				<li><a href="#">Xbox 360</a></li>
			</ul>
		</div>
		<!-- /main (left side) -->
		
		
		
		<!-- footer -->
		<div id="footer">
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot;</p>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
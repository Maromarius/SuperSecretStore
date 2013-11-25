<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<title>Login</title>
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
				<li><a href="#">Manage Inventory</a></li>
				<li><a href="HomePage.jsp">Home</a></li>
				<li><a href="ItemListViewer.jsp">Products</a></li>
				<li><a href="#">Shopping Cart</a></li>
				<li><a class="current" href="Login.jsp">Login</a></li>
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
			
			<div class="clear"></div>
		</div>
		<!-- /screenshots -->
		
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
				<h3>Login</h3>
			  <form method="POST" action="LoginServlet">
			   <table>
			    <tr><td align="center">Username</td>
			    	<td align="center"><input type="text" name="username" size="20" /></td>
			    </tr> 
				<tr><td align="center">Password</td>
					<td align="center"><input type="password" name="password" size="20" /></td>
				</tr> 
			   </table>
				<p></p>
				<p>Please login here.</p>
				<p>
					<input type="submit" value="Submit" />
				</p>
				 </form>
				<p><c:if test="${not empty msg}">
			    <h4>${msg}</h4>
				</c:if>
			  </p>
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
			<p>&copy; 2013 <a href="#">GameStore</a> &middot; All Rights Reserved &middot;</p>
		</div>
		<!-- /footer -->
	</div>
</body>
</html>
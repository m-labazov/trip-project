<html>
<head>
<title>Travel planner</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
		<sec:authorize access="isAnonymous()">
<div id="main-layer">
	<div id="loginFrame">
			    <p>Welcome to Travel planner!</p>
				<form action="action/connect/facebook" method="POST">
				    <input type="hidden" name="scope" value="email,user_friends" />
				    <p><button type="submit">Login with Facebook</button></p>
				</form>
	</div>
</div>
		</sec:authorize>
</body>
</html>
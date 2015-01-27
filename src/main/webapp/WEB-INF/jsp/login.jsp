<html>
<body>
<sec:authorize access="isAnonymous()">
		<form action="action/connect/facebook" method="POST">
		    <input type="hidden" name="scope" value="email,user_friends" />
		    <p><button type="submit">Login with Facebook</button></p>
		</form>
    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
	</fb:login-button>
</sec:authorize>
</body>
</html>
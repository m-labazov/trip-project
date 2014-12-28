<html>
<body>
<script>
  window.fbAsyncInit = function() {
    FB.init({
      appId      : '591751197638034',
      xfbml      : true,
      version    : 'v2.2'
    });
  };

  (function(d, s, id){
     var js, fjs = d.getElementsByTagName(s)[0];
     if (d.getElementById(id)) {return;}
     js = d.createElement(s); js.id = id;
     js.src = "//connect.facebook.net/en_US/sdk.js";
     fjs.parentNode.insertBefore(js, fjs);
   }(document, 'script', 'facebook-jssdk'));
</script>
<sec:authorize access="isAnonymous()">
		<div class="panel panel-default">
        <div class="panel-body">
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <!-- Add Facebook sign in button -->
                    <a href="${pageContext.request.contextPath}/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i>Sign in With Facebook</button></a>
                </div>
            </div>
        </div>
    </div>
    <fb:login-button scope="public_profile,email" onlogin="checkLoginState();">
	</fb:login-button>
</sec:authorize>
</body>
</html>
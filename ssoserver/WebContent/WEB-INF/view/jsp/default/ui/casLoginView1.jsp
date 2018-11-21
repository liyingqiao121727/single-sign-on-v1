<!DOCTYPE html>
<!-- saved from url=(0067)http://www.17sucai.com/preview/668095/2017-07-19/perfect/index.html -->
<html lang="en" class="no-js">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<link rel="stylesheet" type="text/css"
	href="/cas/css/index/normalize.css">
<link rel="stylesheet" type="text/css" href="/cas/css/index/demo(1).css">

<link rel="stylesheet" type="text/css"
	href="/cas/css/index/component.css">
<!--[if IE]>
<script src="js/html5.js"></script>
<![endif]-->
</head>
<body>
	<div class="container demo-1">
		<div class="content">
			<div id="large-header" class="large-header" style="height: 559px;">
				<canvas id="demo-canvas" width="1366" height="559"></canvas>
				<div class="logo_box">
					<h3>Be Based On Apereo CAS</h3>
					<form id="form" name="f" method="post">
						<div class="input_outer">
							<span class="u_user"></span> <input name="username" class="text"
								style="color: #FFFFFF !important" type="text"
								placeholder="username is needed" autocomplete="off" />
						</div>
						<div class="input_outer">
							<span class="us_uer"></span> <input name="password" class="text"
								style="color: #FFFFFF !important; position: absolute; z-index: 100;"
								value="" type="password" placeholder="please input password" />
						</div>

						<input type="hidden" name="lt" value="${loginTicket}" /> 
						<input type="hidden" name="execution" value="${flowExecutionKey}" /> 
						<input type="hidden" name="_eventId" value="submit" />
						<div class="mb2">
						<a class="act-but submit" href="javascript:document.getElementById('form').submit();" style="color: #FFFFFF">Login</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="/cas/js/index/TweenLite.min.js"></script>
	<script src="/cas/js/index/EasePack.min.js"></script>
	<script src="/cas/js/index/rAF.js"></script>
	<script src="/cas/js/index/demo-1.js"></script>

</body>
</html>

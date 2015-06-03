<%-- 
    Document   : angular
    Created on : Apr 29, 2015, 4:42:19 PM
    Author     : cuartz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
  <meta charset="utf-8"/>
  <title>DashBoard</title>
  <link rel="stylesheet" href="libs/bootstrap/css/bootstrap.css"/>
  <link rel="stylesheet" href="assets/font-awesome/font-awesome.css"/>
  <link rel="stylesheet" href="assets/style.css"/>	
 
</head>
<body>
    
    <div class="container">    
          <div ng-view></div>
    </div>
 
	<script src="libs/jquery/jquery.js"></script>
	<script src="libs/bootstrap/bootstrap.js"></script>
	<script src="libs/angular/angular.js"></script>
	<script src="libs/angular-route/angular-route.js"></script>
        <script src="libs/sockjs/sockjs.min.js" type="text/javascript"></script>
        <script src="libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
        <script src="libs/lodash/dist/lodash.min.js"></script>
	<script src="app/app.js"></script>
	<script src="app/controllers.js"></script>
	<script src="app/services.js"></script>
	
</body>
</html>

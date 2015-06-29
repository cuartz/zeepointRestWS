<%-- 
    Document   : angularmaps
    Created on : Apr 29, 2015, 7:32:12 PM
    Author     : cuartz
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>
<html>
    <head>
<!--        <meta charset="utf-8"/>-->
        <title>AngularJS / Google Maps Tutorial</title>
        <!-- AngularJS -->
        <!--script src="js/lib/angularjs.min.js?v=1.2.26"></script-->
        <link href="assets/style.css" rel="stylesheet" type="text/css" />
        <script src="libs/angular/angular.min.js"></script>
 
        <!-- Google Maps -->
        <script src="http://maps.googleapis.com/maps/api/js?key=&sensor=false&extension=.js"></script>
 
        <!-- angular-google-maps -->
        <!--script src="js/lib/lodash.underscore.min.js?v=2.4.1"></script>
        <script src="js/lib/angular-google-maps.min.js?v=1.2.2"></script-->
        <script src="libs/lodash/dist/lodash.min.js"></script>
        <script src="libs/angular-google-maps/angular-google-maps.js"></script>
 
        <!-- Custom angular module -->
        <script src="app/map-module.js"></script>

    </head>
    <body> 
<div ng-app="mapsApp" ng-controller="MapCtrl">
    <div id="map"></div>
    <div ng-repeat="marker in markers"
                    ng-class="{active: $index == markerId}"     >
         <a href="#" ng-click="openInfoWindow($event, marker)">{{marker.title}}</a>
    </div>
</div>
        
    </body>
<!--    <body ng-controller="MapCtrl">
    <google-map center="map.center"
                zoom="map.zoom"
                draggable="true"
                options="map.options"
                control="map.control">
        <marker coords="marker.coords" options="marker.options" idkey="marker.id" ></marker>        
    </google-map>
</body>-->
</html>

        
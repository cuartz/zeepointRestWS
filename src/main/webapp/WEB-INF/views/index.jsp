<!DOCTYPE HTML>
<html lang="en">
    <head>
        <link rel="shortcut icon" href="favicon.ico"/>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="libs/bootstrap/css/bootstrap.css"/>
        <link rel="stylesheet" href="assets/font-awesome/font-awesome.css"/>
        <link rel="stylesheet" href="assets/style.css"/>	
        <script src="https://maps.googleapis.com/maps/api/js"></script>
        <script src="libs/angular/angular.js"></script>
        <script>
//            function initialize() {
//                var mapCanvas = document.getElementById('map-canvas');
//                var center = new google.maps.LatLng(44.5403, -78.5463);
//                var zoom=4;
//                var mapOptions = {
//                    //zoom: 4,
//                    mapTypeId: google.maps.MapTypeId.ROADMAP
//                }
//                var map = new google.maps.Map(mapCanvas, mapOptions)
//
//
//                var myIcon = new google.maps.MarkerImage("images/map_icon2.png", null, null, null, new google.maps.Size(32, 35));
//                if (navigator.geolocation) {
//                    navigator.geolocation.getCurrentPosition(function (position) {
//                        center = new google.maps.LatLng(position.coords.latitude,
//                                position.coords.longitude);
//                        var marker = new google.maps.Marker({
//                            position: center,
//                            map: map,
//                            icon: myIcon,
//                            //zoom: 12
//                            //title: 'Hello World!'
//                        });
//
//
//                        var infowindow = new google.maps.InfoWindow({
//                            map: map,
//                            position: center,
//                            content: 'This is your current location'
//                        });
//
//                        map.setCenter(center);
//                        map.setZoom(12);
//                        zoom=12;
//                    }, function () {
//                        handleNoGeolocation(true);
//                    });
//                } else {
//                    // Browser doesn't support Geolocation
//                    handleNoGeolocation(false);
//                }
//                map.setCenter(center);
//                map.setZoom(zoom);
//            }
//            google.maps.event.addDomListener(window, 'load', initialize);
//
//            function addMarker(feature) {
//                var marker = new google.maps.Marker({
//                    position: feature.position,
//                    icon: icons[feature.type].icon,
//                    map: map
//                });
//            }





        </script>
    </head>
    <body ng-app="chatApp">
        <div ng-controller="MapCtrl">

            <!--            
             <div class="map-canvas" ng-if="locationActive">-->
            <!--    <ui-gmap-google-map draggable="true" center='map.center' zoom='map.zoom' control="control">
                  <ui-gmap-marker coords="markers.coords" idKey="markers.idKey">
                  </ui-gmap-marker>
            <ui-gmap-markers models="markers" coords="'coords'" idKey="'idKey'">
                      </ui-gmap-markers>
                </ui-gmap-google-map>-->

            <!--<ui-gmap-google-map center='map.center' zoom='map.zoom'>
                
                <ui-gmap-markers models="markers" coords="'coords'" idKey="'idKey'">
                      </ui-gmap-markers>
                            <markers models="markers" coords="'self'" click="'onClicked'">
                            <windows show="'showWindow'" closeClick="'closeClick'" ng-cloak>
                                <p ng-non-bindable style="width:200px">{{ options.title }}</p>
                                <p ng-non-bindable>{{ latitude | number:4 }}, {{ longitude | number:4 }}!</p>
                            </windows>
                        </markers>
            </ui-gmap-google-map>-->

            <ui-gmap-google-map   center="map.center" zoom="map.zoom" draggable="true" options="options" bounds="map.bounds" events="events">
                <ui-gmap-markers models="randomMarkers" coords="'self'" icon="'icon'" doCluster="true" 


                                 options="'options'"
                                 idkey='randomMarkers.id' 
                                 clusterOptions='map.clusterOptions'
                                 isLabel='true' click='markerClicked' >

                </ui-gmap-markers> 
                <!--                    <ui-gmap-marker coords="marker.coords" options="marker.options" events="marker.events" idkey="marker.id">
                        </ui-gmap-marker>-->
                <!--            <ui-gmap-marker models="sites" coords="'self'" click="'onClicked'">
                                <windows show="'showWindow'" closeClick="'closeClick'" ng-cloak>
                                    <p ng-non-bindable style="width:200px">{{ options.title }}</p>
                                    <p ng-non-bindable>{{ latitude | number:4 }}, {{ longitude | number:4 }}!</p>
                                </windows>
                            </ui-gmap-marker>-->
            </ui-gmap-google-map>

            <!--  </div>-->
            <!--            <markers models="vehicles" coords="'last_known_location'"></markers>-->
        


        <!--div id="map-canvas" align="center" style="width:100%"></div-->

        <!--div ng-app="mapsApp" ng-controller="MapCtrl">
    <div id="map"></div>
    <div ng-repeat="marker in markers"
                    ng-class="{active: $index == markerId}"     >
         <a href="#" ng-click="openInfoWindow($event, marker)">{{marker.title}}</a>
    </div>
</div-->
        <table style="width:100%">
            <tr>
                <td style="width:30%;vertical-align:top;">
                    <div> <!--ng-controller="channelsCtrl"-->
        <ul class="ca-menu">
            <div ng-repeat="ziPoint in ziPoints">
                    <li>
                        <button ng-click="goToMark(ziPoint)">{{ziPoint.title}}
<!--                            <span class="ca-icon">{{ziPoint.users}}</span>
                            <div class="ca-content">
                                <h2 class="ca-main">{{ziPoint.title}}</h2>
                                <h2 class="ca-sub">{{ziPoint.description}}</h2>
                            </div>-->
<span style="alignment-adjust: right; color: #777">{{ziPoint.users}} users</span>
                        </button>
                        <span class="ca-icon">{{ziPoint.users}}</span>

                    </li>
                    <hr/>
</div>
            </ul>
                    




                    <!--                    	<tbody  ng-controller="channelsCtrl">
                                    <tr ng-repeat="ziPoint in ziPoints">
                                            <td>{{ziPoint.name}}</td>
                                            <td>{{todo.createdOn | date}}</td>
                                            <td><button class="btn btn-danger" ng-click="deleteTodo(todo)">Delete</button></td>
                                    </tr>
                            </tbody-->


                </td>
                <td style="width:40%;vertical-align:top;">
                    <div ng-controller="ChatCtrl" class="container">
                        <form ng-submit="addMessage()" name="messageForm">
                            <input type="text" placeholder="Compose a new message..." ng-model="message" />
                            <div class="channel"><h4>{{ziPoint.title}}</h4></div>
                            <div class="info">
                                <span class="count" ng-bind="max - message.length" ng-class="{danger: message.length > max}">140</span>
                                <button ng-disabled="message.length > max || message.length === 0">Send</button>
                            </div>
                        </form>
                        <hr />
                        <p ng-repeat="message in messages| orderBy:'time':true" class="message">
                            <time>{{message.time| date:'HH:mm'}}</time>
                            <span ng-class="{self: message.self}">{{message.message}}</span>
                        </p>
                    </div>
                </td>
                <td style="width:30%"></td>
            </tr>
        </table>
</div>
           
 
        <script src="libs/jquery/jquery.js"></script>
        <script src="libs/bootstrap/bootstrap.js"></script>
        <script src="libs/angular/angular.js"></script>
        <script src="libs/angular-route/angular-route.js"></script>
        <script src="libs/sockjs/sockjs.min.js" type="text/javascript"></script>
        <script src="libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
        <script src="libs/lodash/dist/lodash.min.js"></script>
        <script src="libs/angular-google-maps/angular-google-maps.js"></script>
        <script src="app/app.js"></script>
        <script src="app/controllers.js"></script>
        <script src="app/services.js"></script>
        <!--script src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script-->
        
    </body>
</html>

(function(angular) {
  angular.module("chatApp", [
    "chatApp.controllers",
    "chatApp.services",//'google-maps'
    'uiGmapgoogle-maps'
  ]);
  
  angular.module("chatApp.controllers", []);
  angular.module("chatApp.services", []);
})(angular);

var myApp = angular.module('myApp',['ngRoute']);
 
myApp.config(['$routeProvider','$locationProvider',
        function($routeProvider, $locationProvider) {
          $routeProvider
          	.when('/home', {
              templateUrl: 'templates/home.html',
              controller: 'TodoController'
          	})
            .otherwise({
            	redirectTo: 'home'
            });
          
}]);

//angular.module('mapsApp', [])
//.controller('MapCtrl', function ($scope) {
//
//    var mapOptions = {
//        zoom: 4,
//        center: new google.maps.LatLng(40.0000, -98.0000),
//        mapTypeId: google.maps.MapTypeId.TERRAIN
//    }
//
//    $scope.map = new google.maps.Map(document.getElementById('map'), mapOptions);
//
//    $scope.markers = [];
//    
//    var infoWindow = new google.maps.InfoWindow();
//    
//    var createMarker = function (info){
//        
//        var marker = new google.maps.Marker({
//            map: $scope.map,
//            position: new google.maps.LatLng(info.lat, info.long),
//            title: info.city
//        });
//        marker.content = '<div class="infoWindowContent">' + info.desc + '</div>';
//        
//        
//        google.maps.event.addListener(marker, 'click', function(){
//            $scope.openInfoWindow(null, marker);
//            $scope.$digest();
//        });
//        
//        $scope.markers.push(marker);
//        
//    }  
//    
//    for (i = 0; i < cities.length; i++){
//        createMarker(cities[i]);
//    }
//
//    $scope.openInfoWindow = function(e, selectedMarker){
//        e && e.preventDefault();
//        
//        infoWindow.setContent('<h2>' + selectedMarker.title + '</h2>' + selectedMarker.content);
//        infoWindow.open($scope.map, selectedMarker);
//        $scope.markerId = $scope.markers.indexOf(selectedMarker);
//        
//    }
//
//});


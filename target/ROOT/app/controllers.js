(function (angular) {
    angular.module("chatApp.controllers").controller("ChatCtrl", function ($scope, ChatService) {
        $scope.messages = [];
        $scope.message = "";
        $scope.max = 140;

        $scope.addMessage = function () {
            ChatService.send($scope.message);
            $scope.message = "";
        };

        ChatService.receive().then(null, null, function (message) {
            $scope.messages.push(message);
        });

        $scope.$on('channelChanged', function (event, ziPoint) {
            $scope.messages = [];
            ChatService.CHANNEL = ziPoint.channelId;
            ChatService.initialize();
        });
    });
})(angular);


(function (angular) {
    angular.module("chatApp.controllers").controller('MapCtrl', ['$scope', '$http', 'MapService', 'ChannelService', function ($scope, $http, MapService, ChannelService) {
            $scope.map = MapService.map;
            //loadZipointsWithAtCHange();//MapService.map.center.latitude,MapService.map.center.longitude);
            //ChannelService.loadZiPs($http);
//                {
//            center: {
//                latitude: 40.1451,
//                longitude: -99.6680
//            },
//            zoom: 12,
//            bounds: {}
//        };
            $scope.options = {
                //scrollwheel: false
            };
            $scope.ziPoint;
            $scope.goToMark = function (ziPoint) {
                $scope.ziPoint = ziPoint;
                $scope.map.center.latitude = ziPoint.latitude;
                $scope.map.center.longitude = ziPoint.longitude;
                $scope.map.zoom = 18;


                $scope.$broadcast('channelChanged', $scope.ziPoint);
            };
            //$scope.randomMarkers = [];
            //$scope.ziPoints =$scope.randomMarkers = ChannelService.ziPoints;

//            $scope.events = {
//                //dragend
//                center_changed
//                : function () {
//                    loadZipointsWithAtCHange();
//                }
//            }

            $scope.$watch(function ($scope) {
                return $scope.map.center.latitude
            },
                    function (newValue, oldValue) {
                        loadZipointsWithAtCHange();
                        //alert('center changer');
                    }
            );


            $scope.markerClicked = function (oldZipoint, event, ziPoint) {
                //$scope.windowOptions.show = !$scope.windowOptions.show;
                //console.log('$scope.windowOptions.show: ', $scope.windowOptions.show);
                //alert(ziPoint.model.longitude);
                $scope.map.center.latitude = ziPoint.latitude;
                $scope.map.center.longitude = ziPoint.longitude;
                $scope.ziPoint = ziPoint;
                //alert('This is a ' + data);
                $scope.$broadcast('channelChanged', $scope.ziPoint);
            };

            function loadZipointsWithAtCHange() {
                //alert(http);
                $http.get('webws/zeepointgroups/getzpoints?lat=' + $scope.map.center.latitude + '&lon=' + $scope.map.center.longitude)
                        .success(function (data, status, headers, config) {
                            //zipies = [];
                            //$scope.ziPoints = data;
                            //$scope.markers
                            //alert(data.zeePointsOut[0].name)
                            //service.ziPoints=[];
                            ChannelService.ziPoints = [];
                            for (i = 0; i < data.zeePointsOut.length; i++) {
                                //alert(i);

                                var newstyle = {
//                    'background-image': 'url("images/mini_icon2.png")',
                                    'background-size': '36.5px 61px',
                                    'background-position': 'top left',
                                    'background-repeat': 'no-repeat'
                                }

                                var ziPoint = {
                                    latitude: data.zeePointsOut[i].latitud,
                                    longitude: data.zeePointsOut[i].longitud,
                                    title: data.zeePointsOut[i].name,
                                    description: data.zeePointsOut[i].description,
                                    users: data.zeePointsOut[i].users,
                                    icon: "images/mini_icon2.png",
                                    channelId:data.zeePointsOut[i].referenceId,
                                    options: {
                                        labelContent: data.zeePointsOut[i].name,
                                        labelAnchor: "36 61",
                                        labelClass: 'labelClass',
                                        labelStyle: newstyle,
                                        labelInBackground: false,
                                        title: data.zeePointsOut[i].name
                                    }
                                };
                                //if (idKey == null) {
                                idKey = "id";
                                //}
                                ziPoint[idKey] = i;
                                //return ret;
                                //service.ziPoints.push(ziPoint);
                                ChannelService.ziPoints.push(ziPoint);
                                if (!$scope.ziPoint) {
                                    $scope.ziPoint = ziPoint;
                                    $scope.$broadcast('channelChanged', $scope.ziPoint);
                                }

                            }
                            $scope.ziPoints = $scope.randomMarkers = ChannelService.ziPoints;
//alert(ChannelService.ziPoints.length);
                        })
                        .error(function (data, status, headers, config) {
                            alert('Error loading ZiPoints services');
                        });
            }
            ;

        }]);
})(angular);


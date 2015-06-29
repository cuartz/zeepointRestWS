(function (angular, SockJS, Stomp, _, undefined) {
    angular.module("chatApp.services").service("ChatService", function ($q, $timeout) {

        var service = {}, listener = $q.defer(), socket = {
            client: null,
            stomp: null
        }, messageIds = [];

        service.RECONNECT_TIMEOUT = 30000;
        service.SOCKET_URL = "/chat";
        service.CHAT_TOPIC = "/topic/channels";
        service.CHAT_BROKER = "/app/chat/";
        service.CHANNEL = "";

        service.receive = function () {
            return listener.promise;
        };

        service.send = function (message) {
            var id = Math.floor(Math.random() * 1000000);
            socket.stomp.send(service.CHAT_BROKER+service.CHANNEL, {
                priority: 9
            }, JSON.stringify({
                message: message,
                id: id,
                channel:service.CHANNEL,
                userId:4,
                fbId:10202968387529451,
                userName:"Kevin Bayona",
                messageType:"0"
            }));
            messageIds.push(id);
        };

        var reconnect = function () {
            $timeout(function () {
                initialize();
            }, this.RECONNECT_TIMEOUT);
        };

        var getMessage = function (data) {
            var message = JSON.parse(data), out = {};
            out.message = message.message;
            out.time = new Date(message.time);
            if (_.contains(messageIds, message.id)) {
                out.self = true;
                messageIds = _.remove(messageIds, message.id);
            }
            return out;
        };

        var startListener = function () {
            socket.stomp.subscribe(service.CHAT_TOPIC+"/"+service.CHANNEL
            , function (data) {
                listener.notify(getMessage(data.body));
            });
        };

        service.initialize = function () {
            if (socket.stomp){
                socket.stomp.disconnect();
            }
            socket.client = new SockJS(service.SOCKET_URL);
            socket.stomp = Stomp.over(socket.client);
            socket.stomp.connect({}, startListener);
            socket.stomp.onclose = reconnect;
        };

        //initialize();
        return service;
    });
})(angular, SockJS, Stomp, _);



(function () {
    angular.module("chatApp.services").service("MapService", function () {

        var service = {};
        service.LATITUDE = 44.5403;
        service.LONGITUDE = -78.5463;
        service.map = {
            center: {
                latitude: service.LATITUDE,
                longitude: service.LONGITUDE
            },
            zoom: 4,
            bounds: {}
        };

        service.getLocation = function (map) {
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function (position) {
                    map.center = {
                        latitude: position.coords.latitude,
                        longitude: position.coords.longitude
                    };
                    map.zoom = 14;

                }, function () {
                    handleNoGeolocation(true);
                });
            } else {
                // Browser doesn't support Geolocation
                handleNoGeolocation(false);
            }
        }


        return service;
    });
})();

(function () {
    angular.module("chatApp.services").service("ChannelService", function () {

        var service = {};
        service.ziPoints=[];

//        service.loadZiPs = function (http, latitude, longitude) {
//            //alert(http);
//            http.get('zeepointgroups/getzpoints?lat=' + this.LATITUDE + '&lon=' + this.LONGITUDE)
//                    .success(function (data, status, headers, config) {
//                        zipies=[];
//                        //$scope.ziPoints = data;
//                        //$scope.markers
//                        //alert(data.zeePointsOut[0].name)
//                        //service.ziPoints=[];
//
//                        for (i = 0; i < data.zeePointsOut.length; i++) {
//                            //alert(i);
//
//                            var newstyle = {
////                    'background-image': 'url("images/mini_icon2.png")',
//                                'background-size': '36.5px 61px',
//                                'background-position': 'top left',
//                                'background-repeat': 'no-repeat'
//                            }
//
//                            var ziPoint = {
//                                latitude: data.zeePointsOut[i].latitud,
//                                longitude: data.zeePointsOut[i].longitud,
//                                title: data.zeePointsOut[i].name,
//                                description: data.zeePointsOut[i].description,
//                                users: data.zeePointsOut[i].distance, //data.zeePointsOut[i].users,
//                                icon: "images/mini_icon2.png",
//                                options: {
//                                    labelContent: 'dist<br />date',
//                                    labelAnchor: "36 61",
//                                    labelClass: 'labelClass',
//                                    labelStyle: newstyle,
//                                    labelInBackground: false,
//                                    title: 'asda'
//                                }
//                            };
//                            //if (idKey == null) {
//                            idKey = "id";
//                            //}
//                            ziPoint[idKey] = i;
//                            //return ret;
//                            //service.ziPoints.push(ziPoint);
//                            service.ziPoints.push(ziPoint);
//
//                        }
//
////alert(service.ziPoints.length);
//                    })
//                    .error(function (data, status, headers, config) {
//                        alert('Error loading Todos');
//                    });
//        };



        return service;
    });
})();
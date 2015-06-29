<%-- 
    Document   : prueba
    Created on : Apr 27, 2015, 3:51:26 AM
    Author     : cuartz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript">
            function WebSocketTest()
            {
                if ("WebSocket" in window)
                {
                    alert("WebSocket is supported by your Browser!");
                    // Let us open a web socket
                    //var ws = new WebSocket("ws://localhost:8080/zeepoint/chat/websocket");
                    var ws = new WebSocket("ws://localhost:8080/zeepoint/chat/websocket");
                    var stompClient = Stomp.over(ws);
                    stompClient.connect(' ', ' ', function (frame) {

                        stompClient.subscribe("/topic/message", function (message) {

                        });
                        stompClient.send('/app/chat', {}, JSON.stringify({
                            message: "message",
                            id: 1
                        }));
                    }, function (error) {

                    });


                    /*
                     ws.onopen = function()
                     {
                     // Web Socket is connected, send data using send()
                     ws.send("Message to send");
                     alert("Message is sent...");
                     };
                     
                     ws.onmessage = function (evt) 
                     { 
                     var received_msg = evt.data;
                     alert("Message is received...");
                     };
                     
                     ws.onclose = function()
                     { 
                     // websocket is closed.
                     alert("Connection is closed..."); 
                     };
                     */

                }
                else

                {
                    // The browser doesn't support WebSocket
                    alert("WebSocket NOT supported by your Browser!");
                }
            }
        </script>

    </head>

    <body>
        <div id="sse">
            <a href="javascript:WebSocketTest()">Run WebSocket</a>
        </div>
        <script src="libs/stomp-websocket/lib/stomp.min.js" type="text/javascript"></script>
    </body>

</html>

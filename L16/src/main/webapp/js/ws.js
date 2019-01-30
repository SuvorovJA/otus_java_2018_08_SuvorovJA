var wsocket;

function wsconnect() {
    wsocket = new WebSocket("ws://localhost:8080/ws");
    wsocket.onmessage = onMessage;
}

function onMessage(evt) {
    document.getElementById("wsSearchResponse").innerHTML = evt.data;
}

function wssend(){
    if(wsocket.readyState === wsocket.CLOSED) wsconnect();
    while(wsocket.readyState === wsocket.CONNECTING);
    var request = document.getElementById("wsSearchUsername").value;
    wsocket.send(request);
}

window.addEventListener("load", wsconnect, false);
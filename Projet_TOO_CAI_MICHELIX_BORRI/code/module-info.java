module BCMS {
    requires java.sql;
    requires PauWareTwo;
    opens com.MichelixBorri.BCMS to PauWareTwo;
    requires java.desktop;
    //requires java.logging;
    requires java.net.http;
    requires java.json;
    requires javax.websocket.api;
    requires tyrus.server;
}

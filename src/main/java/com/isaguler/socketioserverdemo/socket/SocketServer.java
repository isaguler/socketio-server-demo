package com.isaguler.socketioserverdemo.socket;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.isaguler.socketioserverdemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SocketServer {

    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketServer(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onMessageReceived());
    }

    private DataListener<Message> onMessageReceived() {
        return (socketIOClient, message, ackRequest) -> {
            log.info("message: " + message);

            socketService.saveMessage(socketIOClient, message);
        };
    }

    private ConnectListener onConnected() {
        return socketIOClient -> {
            Map<String, List<String>> urlParams = socketIOClient.getHandshakeData().getUrlParams();

            String room = String.join("", urlParams.get("room"));
            String username = String.join("", urlParams.get("username"));

            socketIOClient.joinRoom(room);

            socketService.saveInfoMessage(socketIOClient, "welcome " + username, room);

            log.info("connected: {} {} {}", socketIOClient.getSessionId(), room, username);
        };
    }

    private DisconnectListener onDisconnected() {
        return socketIOClient -> {
            Map<String, List<String>> urlParams = socketIOClient.getHandshakeData().getUrlParams();

            String room = String.join("", urlParams.get("room"));
            String username = String.join("", urlParams.get("username"));

            socketService.saveInfoMessage(socketIOClient, "welcome " + username, room);

            log.info("disconnected: {} {} {}", socketIOClient.getSessionId(), room, username);
        };
    }
}

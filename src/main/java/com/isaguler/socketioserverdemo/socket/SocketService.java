package com.isaguler.socketioserverdemo.socket;

import com.corundumstudio.socketio.SocketIOClient;
import com.isaguler.socketioserverdemo.model.Message;
import com.isaguler.socketioserverdemo.model.MessageType;
import com.isaguler.socketioserverdemo.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SocketService {

    private final MessageService messageService;

    public void saveInfoMessage(SocketIOClient socketIOClient, String message, String room) {

        Message savedMessage = messageService.saveMessage(Message.builder()
                .messageType(MessageType.SERVER)
                .content(message)
                .room(room)
                .build());

        this.sendSocketMessage(socketIOClient, savedMessage, room);
    }

    public void saveMessage(SocketIOClient socketIOClient, Message message) {
        Message savedMessage = messageService.saveMessage(Message.builder()
                .messageType(MessageType.CLIENT)
                .content(message.getContent())
                .room(message.getRoom())
                .build());

        this.sendSocketMessage(socketIOClient, savedMessage, message.getRoom());
    }

    public void sendSocketMessage(SocketIOClient socketIOClient, Message message, String room) {
        for (SocketIOClient client : socketIOClient.getNamespace().getRoomOperations(room).getClients()) {
            if (!client.getSessionId().equals(socketIOClient.getSessionId())) {
                client.sendEvent("read_message", message);
            }
        }
    }
}

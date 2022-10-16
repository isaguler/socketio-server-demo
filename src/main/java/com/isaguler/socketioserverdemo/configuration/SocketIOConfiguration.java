package com.isaguler.socketioserverdemo.configuration;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${socket.server.host}")
    private String host;

    @Value("${socket.server.port}")
    private int port;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        return new SocketIOServer(config);
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        socketIOServer().start();
    }
}

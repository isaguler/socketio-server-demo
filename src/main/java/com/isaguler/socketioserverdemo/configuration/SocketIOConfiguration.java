package com.isaguler.socketioserverdemo.configuration;

import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Configuration
public class SocketIOConfiguration implements ApplicationListener<ApplicationReadyEvent> {

    /*@Value("${socket.server.host}")
    private String host;*/

    private final String host;

    @Value("${socket.server.port}")
    private int port;

    public SocketIOConfiguration() throws UnknownHostException {
        this.host = InetAddress.getLocalHost().getHostAddress();
    }

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

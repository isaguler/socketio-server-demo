package com.isaguler.socketioserverdemo.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Date createdDateTime;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String content;

    private String room;

    private String username;
}

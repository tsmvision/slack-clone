package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "channel_chats")
@Getter
@Setter
// TODO: setup index
// TODO: setup cascade
public class ChannelChat extends BaseEntity {
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_channel_chat__user"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", foreignKey = @ForeignKey(name="fk_channel_chat__channel"))
    private Channel channel;
}

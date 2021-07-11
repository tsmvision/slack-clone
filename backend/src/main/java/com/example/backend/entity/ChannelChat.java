package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

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
    @Setter(AccessLevel.NONE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", foreignKey = @ForeignKey(name="fk_channel_chat__channel"))
    @Setter(AccessLevel.NONE)
    private Channel channel;

    @OneToMany(mappedBy = "chat")
    @Setter(AccessLevel.NONE)
    private List<Mention> mentionList;

    public void saveUser(User user) {
        this.user = user;
        this.user.getChannelChatList().add(this);
    }

    public void saveChannel(Channel channel) {
        this.channel = channel;
        this.channel.getChannelChatList().add(this);
    }
}

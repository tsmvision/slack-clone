package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "channel_members")
@Getter
@Setter
// TODO: add cascade
public class ChannelMember extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", foreignKey = @ForeignKey(name="fk_channel_member__channel"))
    @Setter(AccessLevel.NONE)
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter(AccessLevel.NONE)
    private User user;

    public void saveChannel(Channel channel) {
        this.channel = channel;
        this.channel.getChannelMembers().add(this);
    }

    public void saveUser(User user) {
        this.user = user;
        this.user.getChannelMembers().add(this);
    }
}

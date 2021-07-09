package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "channels")
@Getter
@Setter
// TODO: add cascade
public class Channel extends BaseEntity {
    @Column(name = "name", length = 30)
    @Size(max = 30)
    private String name;

    @Column(name= "is_private")
    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_channel__workspace"))
    private Workspace workspace;

    @OneToMany(mappedBy="user")
    private List<ChannelMember> channelMembers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_channel__user"))
    private User user;

    @OneToMany(mappedBy = "channel")
    private List<ChannelChat> channelChatList;
}

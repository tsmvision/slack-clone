package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "channels")
@Getter
@Setter
// TODO: add cascade
public class Channel extends BaseEntity {
    @Column(name = "name", length = 30)
    @Size(max = 30)
    @NotNull
    private String name;

    @Column(name= "is_private")
    private Boolean isPrivate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_channel__workspace"))
    @Setter(AccessLevel.NONE)
    private Workspace workspace;

    @OneToMany(mappedBy="user")
    @Setter(AccessLevel.NONE)
    private List<ChannelMember> channelMembers = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_channel__user"))
    @Setter(AccessLevel.NONE)
    private User user;

    @OneToMany(mappedBy = "channel")
    @Setter(AccessLevel.NONE)
    private List<ChannelChat> channelChatList = new ArrayList<>();

    public void saveWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspace.getChannels().add(this);
    }

    public void saveUser(User user) {
        this.user = user;
        this.user.getChannels().add(this);
    }
}

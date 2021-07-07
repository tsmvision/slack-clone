package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
// TODO: add cascade
// TODO: add indices
public class User extends BaseEntity {
    @Column(name = "email", length = 30, unique = true)
    @Size(max = 30)
    private String email;

    @Column(name = "nickname", length = 30)
    @Size(max = 30)
    private String nickname;

    @Column(name = "password", length = 100)
    @Size(max = 100)
    private String password;

    @OneToMany(mappedBy = "user")
    private List<ChannelChat> channelChatList;

    @OneToMany(mappedBy = "user")
    private List<ChannelMember> channelMembers;

    @OneToMany(mappedBy = "sender")
    private List<DirectMessage> directMessageFromSenders;

    @OneToMany(mappedBy = "receiver")
    private List<DirectMessage> directMessageFromReceivers;

    @OneToMany(mappedBy = "user")
    private List<WorkspaceMember> workspaceMembers;

    @OneToMany(mappedBy = "user")
    private List<Channel> channels;

    @OneToMany(mappedBy = "sender")
    private List<Mention> MentionsFromSender;

    @OneToMany(mappedBy = "receiver")
    private List<Mention> MentionsFromReceiver;

    @OneToMany(mappedBy = "owner")
    private List<UserWorkspace> userWorkspaces;
}

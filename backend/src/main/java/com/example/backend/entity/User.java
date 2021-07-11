package com.example.backend.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    @Setter(AccessLevel.NONE)
    private List<ChannelChat> channelChatList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private List<ChannelMember> channelMembers = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    @Setter(AccessLevel.NONE)
    private List<DirectMessage> directMessageFromSenders = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @Setter(AccessLevel.NONE)
    private List<DirectMessage> directMessageFromReceivers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private List<WorkspaceMember> workspaceMembers = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Setter(AccessLevel.NONE)
    private List<Channel> channels = new ArrayList<>();

    @OneToMany(mappedBy = "sender")
    @Setter(AccessLevel.NONE)
    private List<Mention> MentionsFromSender = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @Setter(AccessLevel.NONE)
    private List<Mention> MentionsFromReceiver = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    @Setter(AccessLevel.NONE)
    private List<UserWorkspace> userWorkspaces = new ArrayList<>();
}

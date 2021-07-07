package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "workspaces")
@Getter
@Setter
public class Workspace extends BaseEntity {
    // TODO: double check unique
    @Column(name = "name", length = 30, unique = true)
    @Size(max = 30)
    private String name;

    @Column(name = "url", length = 30, unique = true)
    @Size(max = 30)
    private String url;

    @OneToMany(mappedBy = "workspace")
    private List<Channel> channels;

    @OneToMany(mappedBy = "workspace")
    private List<DirectMessage> directMessageList;

    @OneToMany(mappedBy = "workspace")
    private List<WorkspaceMember> workspaceMembers;

    @OneToMany(mappedBy = "workspace")
    private List<UserWorkspace> userWorkspaces;
}

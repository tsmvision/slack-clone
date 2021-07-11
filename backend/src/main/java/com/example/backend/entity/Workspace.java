package com.example.backend.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "workspaces")
@Getter
@Setter
@NoArgsConstructor
public class Workspace extends BaseEntity {
    // TODO: double check unique
    @Column(name = "name", length = 30, unique = true)
    @Size(max = 30)
    private String name;

    @Column(name = "url", length = 30, unique = true)
    @Size(max = 30)
    private String url;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "owner_id", foreignKey = @ForeignKey(name="fk_workspace__owner"))
//    @Setter(AccessLevel.NONE)
//    private User owner;

    @OneToMany(mappedBy = "workspace")
    @Setter(AccessLevel.NONE)
    private List<Channel> channels = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    @Setter(AccessLevel.NONE)
    private List<DirectMessage> directMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    @Setter(AccessLevel.NONE)
    private List<WorkspaceMember> workspaceMembers = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    @Setter(AccessLevel.NONE)
    private List<UserWorkspace> userWorkspaces = new ArrayList<>();

    @OneToMany(mappedBy = "workspace")
    @Setter(AccessLevel.NONE)
    private List<Mention> mentions = new ArrayList<>();

}

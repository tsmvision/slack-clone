package com.example.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workspace_members")
@Getter
@Setter
@NoArgsConstructor
// TODO: add cascade
// TODO: add indeices

public class WorkspaceMember extends BaseEntity {

    @Column(name = "loggedin_at")
    private LocalDate loggedInAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_workspace_member__workspace"))
    @Setter(AccessLevel.NONE)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_workspace_member__user"))
    @Setter(AccessLevel.NONE)
    private User user;

    public void saveWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspace.getWorkspaceMembers().add(this);
    }

    public void saveUser(User user) {
        this.user = user;
        this.user.getWorkspaceMembers().add(this);
    }
}

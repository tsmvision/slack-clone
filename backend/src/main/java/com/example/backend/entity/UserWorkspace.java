package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "user_workspace")
@Getter
@Setter
public class UserWorkspace extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @Setter(AccessLevel.NONE)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_user_workspace__workspace"))
    @Setter(AccessLevel.NONE)
    private Workspace workspace;

    public void saveOwner(User owner) {
        this.owner = owner;
        this.owner.getUserWorkspaces().add(this);
    }

    public void saveWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspace.getUserWorkspaces().add(this);
    }
}

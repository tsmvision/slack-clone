package com.example.backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "workspace_members")
@Getter
@Setter
// TODO: add cascade
// TODO: add indeices
public class WorkspaceMember extends BaseEntity {

    @Column(name = "loggedin_at")
    private LocalDate loggedInAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_workspace_member__workspace"))
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name="fk_workspace_member__user"))
    private User user;
}

package com.example.backend.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "direct_messages")
@Getter
@Setter
// TODO: add cascade
// TODO: add indexes
public class DirectMessage extends BaseEntity {

    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_direct_message__workspace"))
    @Setter(AccessLevel.NONE)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name="fk_direct_message__sender"))
    @Setter(AccessLevel.NONE)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name="fk_direct_message__receiver"))
    @Setter(AccessLevel.NONE)
    private User receiver;

    public void saveWorkspace(Workspace workspace) {
        this.workspace = workspace;
        this.workspace.getDirectMessageList().add(this);
    }

    public void saveSender(User sender) {
        this.sender = sender;
        this.sender.getDirectMessageFromSenders().add(this);
    }

    public void saveReceiver(User receiver) {
        this.receiver = receiver;
        this.receiver.getDirectMessageFromReceivers().add(this);
    }
}

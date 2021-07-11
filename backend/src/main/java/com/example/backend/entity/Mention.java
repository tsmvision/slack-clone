package com.example.backend.entity;

import com.example.backend.enums.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "mentions")
@Getter
@Setter
// TODO: add cascade
//
// TODO: double check the relation
public class Mention extends BaseEntity {

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id", foreignKey = @ForeignKey(name="fk_mention__workspace"))
    @Setter(AccessLevel.NONE)
    private Workspace workspace;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", foreignKey = @ForeignKey(name="fk_mention__sender"))
    @Setter(AccessLevel.NONE)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", foreignKey = @ForeignKey(name="fk_mention__receiver"))
    @Setter(AccessLevel.NONE)
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id", foreignKey = @ForeignKey(name="fk_mention__chat"))
    @Setter(AccessLevel.NONE)
    private ChannelChat chat;

    public void saveWorkspace(Workspace workspace){
        this.workspace = workspace;
        this.workspace.getMentions().add(this);
    }

    public void saveSender(User sender) {
        this.sender = sender;
        this.sender.getMentionsFromSender().add(this);
    }

    public void saveReceiver(User receiver) {
        this.receiver = receiver;
        this.receiver.getMentionsFromReceiver().add(this);
    }

    public void saveChat(ChannelChat chat) {
        this.chat = chat;
        this.chat.getMentionList().add(this);
    }
}

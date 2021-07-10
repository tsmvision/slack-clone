create table channel_chats
(
    id         bigserial not null,
    created_at date      not null,
    deleted_at date,
    updated_at date,
    content    varchar(255),
    channel_id int8,
    user_id    int8,
    primary key (id)
);

create table channel_members
(
    id         bigserial not null,
    created_at date      not null,
    deleted_at date,
    updated_at date,
    channel_id int8,
    user_id    int8,
    primary key (id)
);

create table channels
(
    id           bigserial not null,
    created_at   date      not null,
    deleted_at   date,
    updated_at   date,
    is_private   boolean,
    name         varchar(30),
    user_id      int8,
    workspace_id int8,
    primary key (id)
);

create table direct_messages
(
    id           bigserial not null,
    created_at   date      not null,
    deleted_at   date,
    updated_at   date,
    content      varchar(255),
    receiver_id  int8,
    sender_id    int8,
    workspace_id int8,
    primary key (id)
);

create table mentions
(
    id           bigserial not null,
    created_at   date      not null,
    deleted_at   date,
    updated_at   date,
    category     varchar(255),
    chat_id      int8,
    receiver_id  int8,
    sender_id    int8,
    workspace_id int8,
    primary key (id)
);

create table user_workspace
(
    id           bigserial not null,
    created_at   date      not null,
    deleted_at   date,
    updated_at   date,
    user_id      int8,
    workspace_id int8,
    primary key (id)
);

create table users
(
    id         bigserial not null,
    created_at date      not null,
    deleted_at date,
    updated_at date,
    email      varchar(30) unique,
    nickname   varchar(30),
    password   varchar(100),
    primary key (id)
);

create table workspace_members
(
    id           bigserial not null,
    created_at   date      not null,
    deleted_at   date,
    updated_at   date,
    loggedin_at  date,
    user_id      int8,
    workspace_id int8,
    primary key (id)
);

create table workspaces
(
    id         bigserial not null,
    created_at date      not null,
    deleted_at date,
    updated_at date,
    name       varchar(30) unique,
    url        varchar(30) unique,
    owner_id   int8,
    primary key (id)
);

alter table channel_chats
    add constraint fk_channel_chat__channel
        foreign key (channel_id)
            references channels;

alter table channel_chats
    add constraint fk_channel_chat__user
        foreign key (user_id)
            references users;

alter table channel_members
    add constraint fk_channel_member__channel
        foreign key (channel_id)
            references channels;

alter table channel_members
    add constraint FKjunpxp98pv6g23t5d3w5hx8ji
        foreign key (user_id)
            references users;

alter table channels
    add constraint fk_channel__user
        foreign key (user_id)
            references users;

alter table channels
    add constraint fk_channel__workspace
        foreign key (workspace_id)
            references workspaces;

alter table direct_messages
    add constraint fk_direct_message__receiver
        foreign key (receiver_id)
            references users;

alter table direct_messages
    add constraint fk_direct_message__sender
        foreign key (sender_id)
            references users;

alter table direct_messages
    add constraint fk_direct_message__workspace
        foreign key (workspace_id)
            references workspaces;

alter table mentions
    add constraint fk_mention__chat
        foreign key (chat_id)
            references channel_chats;

alter table mentions
    add constraint fk_mention__receiver
        foreign key (receiver_id)
            references users;

alter table mentions
    add constraint fk_mention__sender
        foreign key (sender_id)
            references users;

alter table mentions
    add constraint fk_mention__workspace
        foreign key (workspace_id)
            references workspaces;

alter table user_workspace
    add constraint FKqmqq3m642y2uay2hy7sickk72
        foreign key (user_id)
            references users;

alter table user_workspace
    add constraint fk_user_workspace__workspace
        foreign key (workspace_id)
            references workspaces;

alter table workspace_members
    add constraint fk_workspace_member__user
        foreign key (user_id)
            references users;

alter table workspace_members
    add constraint fk_workspace_member__workspace
        foreign key (workspace_id)
            references workspaces;

alter table workspaces
    add constraint fk_workspace__owner
        foreign key (owner_id)
            references users;

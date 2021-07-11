package com.example.backend.repository;

import com.example.backend.dto.DirectMessageResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.backend.entity.QDirectMessage.directMessage;
import static com.example.backend.entity.QUser.user;
import static com.example.backend.entity.QWorkspace.workspace;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class DirectMessageRepositoryImpl implements DirectMessageRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<DirectMessageResponseDto> findDirectMessagesByUrlAndSenderId(String url, Long senderId) {
        return queryFactory
                .from(directMessage)
                .select(
                        Projections.constructor(
                                DirectMessageResponseDto.class,
                                directMessage.id
                        )
                )
                .leftJoin(directMessage.sender, user)
                .leftJoin(directMessage.workspace, workspace)
                .where(
                        CustomExpressions.userIdEq(senderId),
                        CustomExpressions.workspaceUrlEq(url)
                )
                .fetch();
    }

    @Override
    public List<DirectMessageResponseDto> findWorkspaceDirectMessageChats(
            String url, Long senderId, Long receiverId, Pageable pageable) {
        return queryFactory
                .from(directMessage)
                .select(
                        Projections.constructor(
                                DirectMessageResponseDto.class,
                                directMessage.id
                        )
                )
                .join(directMessage.sender, user)
                .join(directMessage.sender, user)
                .where(
                        CustomExpressions.workspaceUrlEq(url),
                        senderIdEq(senderId),
                        receiverIdEq(receiverId)
                        )
                .orderBy(directMessage.createdAt.desc())
                // TODO: add pagination
                .fetch();
    }

    @Override
    public List<DirectMessageResponseDto> createWorkspaceDirectMessageChats(
            String url, String content, String receiverId, String senderId) {
        // get workspace by workspace by url
        // create DirectMessage
        // add data to the DirectMessage
        // save direct message
        // find direct message by
        return null;
    }

    @Override
    public Long getDirectMessageUnreadsCount(String url, Long senderId, Long receiverId, LocalDate dateAfter) {
        return queryFactory
                .from(directMessage)
                .join(directMessage.workspace, workspace)
                .join(directMessage.sender, user)
                .join(directMessage.receiver, user)
                .where(
                        senderIdEq(senderId),
                        receiverIdEq(receiverId),
                        createdAtDayAfter(dateAfter)
                )
                .fetchCount();
    }

    private BooleanExpression senderIdEq(Long senderId) {
        return directMessage.sender.id.eq(senderId);
    }

    private BooleanExpression receiverIdEq(Long receiverId) {
        return directMessage.receiver.id.eq(receiverId);
    }

    private BooleanExpression createdAtDayAfter(LocalDate dateAfter) {
        return directMessage.createdAt.after(dateAfter.atStartOfDay());
    }
}

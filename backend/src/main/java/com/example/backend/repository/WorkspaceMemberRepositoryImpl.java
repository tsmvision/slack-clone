package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.example.backend.entity.QWorkspaceMember.workspaceMember;
import static com.example.backend.entity.QWorkspace.workspace;
import static com.example.backend.entity.QUser.user;
import java.util.List;

@RequiredArgsConstructor
public class WorkspaceMemberRepositoryImpl implements WorkspaceMemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<WorkspaceMemberResponseDto> findMyWorkspacesByUserId(Long id) {
        return queryFactory
                .from(workspaceMember)
                .select(
                        Projections.constructor(
                                WorkspaceMemberResponseDto.class,
                                workspaceMember.id
                        )
                )
                .fetch();
    }

    @Override
    public List<WorkspaceMemberResponseDto> findWorkspaceMembersByUrl(String url) {
        return queryFactory
                .from(workspaceMember)
                .select(
                        Projections.constructor(
                                WorkspaceMemberResponseDto.class,
                                workspaceMember.id
                        )
                )
                .join(workspaceMember.user, user)
                .join(workspaceMember.workspace, workspace)
                .where(CustomExpressions.workspaceUrlEq(url))
                .fetch();
    }

    @Override
    public List<WorkspaceMemberResponseDto> findWorkspaceMembers() {
        return queryFactory
                .from(workspaceMember)
                .select(
                        Projections.constructor(
                                WorkspaceMemberResponseDto.class,
                                workspaceMember.id
                        )
                )
                .fetch();
    }

    @Override
    public List<WorkspaceMemberResponseDto> findWorkspaceMemberByUrlAndChannelName(String url, String channelName) {
        return queryFactory
                .from(workspaceMember)
                .select(
                        Projections.constructor(
                                WorkspaceMemberResponseDto.class,
                                workspaceMember.id
                        )
                )
                .join(workspaceMember.user, user)
                .join(workspaceMember.workspace, workspace)
                .where(
                        CustomExpressions.channelNameEq(channelName),
                        CustomExpressions.workspaceUrlEq(url)
                )
                .fetch();
    }
}

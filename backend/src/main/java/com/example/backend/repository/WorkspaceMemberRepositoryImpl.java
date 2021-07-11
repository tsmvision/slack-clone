package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
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
                .where(workspaceUrlEq(url))
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
    public List<WorkspaceMemberResponseDto> findWorkspaceMemberByUrlAndUserId(String url, Long userId) {
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
                .where(userIdEq(userId), workspaceUrlEq(url))
                .fetch();
    }

    private BooleanExpression userIdEq(Long id) {
        return user.id.eq(id);
    }

    private BooleanExpression workspaceUrlEq(String url) {
        return workspace.url.eq(url);
    }
}

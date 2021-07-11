package com.example.backend.repository;

import com.example.backend.dto.WorkspaceDto;
import static com.example.backend.entity.QWorkspace.workspace;
import static com.example.backend.entity.QWorkspaceMember.workspaceMember;
import static com.example.backend.entity.QUser.user;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    public List<WorkspaceDto> findMyWorkspacesByUserId(Long id) {
        return queryFactory
                .select(Projections.constructor(WorkspaceDto.class,
                        workspace.id,
                        workspace.name,
                        workspace.url
                        )
                )
                .from(workspace)
                .join(workspace.workspaceMembers, workspaceMember)
                .join(workspaceMember.user, user)
                .where(userIdEq(id))
                .fetch();
    }

    private BooleanExpression userIdEq(Long id) {
        return user.id.eq(id);
    }
}

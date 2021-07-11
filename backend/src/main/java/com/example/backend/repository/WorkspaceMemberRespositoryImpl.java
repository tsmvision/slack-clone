package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.example.backend.entity.QWorkspaceMember.workspaceMember;
import java.util.List;

@RequiredArgsConstructor
public class WorkspaceMemberRespositoryImpl implements WorkspaceMemberRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<WorkspaceMemberDto> findMyWorkspacesByUserId(Long id) {
        return queryFactory
                .from(workspaceMember)
                .select(
                        Projections.constructor(
                                WorkspaceMemberDto.class,
                                workspaceMember.id
                        )
                )
                .fetch();
    }
}

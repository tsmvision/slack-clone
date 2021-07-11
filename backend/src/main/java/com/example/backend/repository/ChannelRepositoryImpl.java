package com.example.backend.repository;

import com.example.backend.dto.ChannelResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static com.example.backend.entity.QChannel.channel;
import static com.example.backend.entity.QWorkspace.workspace;
import java.util.List;

@RequiredArgsConstructor
public class ChannelRepositoryImpl implements ChannelRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChannelResponseDto> findChannelAndWorkspaceById(Long id) {
        return queryFactory
                .select(
                        Projections.constructor(
                                ChannelResponseDto.class,
                                channel.id
                        )
                )
                .from(channel)
                .join(channel.workspace, workspace)
                .where(channelIdEq(id))
                .fetch();
    }

    private BooleanExpression channelIdEq(Long id) {
        return channel.id.eq(id);
    }
}

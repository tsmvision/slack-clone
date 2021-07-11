package com.example.backend.repository;

import com.querydsl.core.types.dsl.BooleanExpression;

import static com.example.backend.entity.QUser.user;
import static com.example.backend.entity.QWorkspace.workspace;

public class CustomExpressions {
    public static BooleanExpression userIdEq(Long id) {
        return user.id.eq(id);
    }
    public static BooleanExpression workspaceUrlEq(String url) {
        return workspace.url.eq(url);
    }
}

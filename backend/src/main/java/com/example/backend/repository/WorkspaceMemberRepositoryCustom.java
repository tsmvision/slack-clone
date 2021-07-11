package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberDto;
import com.example.backend.entity.WorkspaceMember;

import java.util.List;

public interface WorkspaceMemberRepositoryCustom {
    List<WorkspaceMemberDto> findMyWorkspacesByUserId(Long id);
}

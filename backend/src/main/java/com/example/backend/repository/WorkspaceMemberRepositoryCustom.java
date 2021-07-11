package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberDto;
import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.example.backend.entity.Workspace;
import com.example.backend.entity.WorkspaceMember;

import java.util.List;

public interface WorkspaceMemberRepositoryCustom {
    List<WorkspaceMemberResponseDto> findMyWorkspacesByUserId(Long id);
    List<WorkspaceMemberResponseDto> findWorkspaceMembersByUrl(String url);
    List<WorkspaceMemberResponseDto> findWorkspaceMembers();
    List<WorkspaceMemberResponseDto> findWorkspaceMemberByUrlAndUserId(String url, Long userId);
}

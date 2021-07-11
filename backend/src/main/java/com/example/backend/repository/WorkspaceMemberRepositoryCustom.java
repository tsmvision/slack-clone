package com.example.backend.repository;

import com.example.backend.dto.WorkspaceMemberResponseDto;;
import java.util.List;

public interface WorkspaceMemberRepositoryCustom {
    List<WorkspaceMemberResponseDto> findMyWorkspacesByUserId(Long id);
    List<WorkspaceMemberResponseDto> findWorkspaceMembersByUrl(String url);
    List<WorkspaceMemberResponseDto> findWorkspaceMembers();
    List<WorkspaceMemberResponseDto> findWorkspaceMemberByUrlAndChannelName(String url, String channelName);
}

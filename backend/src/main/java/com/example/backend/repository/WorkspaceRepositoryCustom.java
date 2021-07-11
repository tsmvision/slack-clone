package com.example.backend.repository;

import com.example.backend.dto.WorkspaceDto;

import java.util.List;

public interface WorkspaceRepositoryCustom {
    List<WorkspaceDto> findMyWorkspacesByUserId(Long id);
}

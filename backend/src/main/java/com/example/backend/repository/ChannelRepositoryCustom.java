package com.example.backend.repository;

import com.example.backend.dto.ChannelResponseDto;
import java.util.List;

public interface ChannelRepositoryCustom {
    List<ChannelResponseDto> findChannelAndWorkspaceById(Long id);
}

package com.example.backend.repository;

import com.example.backend.dto.DirectMessageResponseDto;

import java.awt.print.Pageable;
import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DirectMessageRepositoryCustom {
    List<DirectMessageResponseDto> findDirectMessagesByUrlAndSenderId(String url, Long userId);
    List<DirectMessageResponseDto> findWorkspaceDirectMessageChats(String url, Long id, Long userId, Pageable pageable);
    List<DirectMessageResponseDto> createWorkspaceDirectMessageChats(
            String url, String content, String receiverId, String senderId);
    Long getDirectMessageUnreadsCount(String url, Long id, Long userId, LocalDate dateSince);
}

package com.example.backend.service;

import com.example.backend.dto.WorkspaceRequestDto;
import com.example.backend.entity.User;
import com.example.backend.entity.Workspace;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public void createWorkspace(WorkspaceRequestDto workspaceRequestDto) throws Exception {
        // get owner with workspaceRequestDto.userId

        // create workspace
        //  add url
        //  add owner

        // create workspaceMember
        //  add owner
        //  add workspace
        // save workspaceMember

        // create channel
        //  add name = "general"
        //  add workspace
        // save channel

        // create channelMember
        //  add owner
        //  channel
        // save channelMember
    }
}

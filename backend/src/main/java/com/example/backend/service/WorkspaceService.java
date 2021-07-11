package com.example.backend.service;

import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.example.backend.dto.WorkspaceRequestDto;
import com.example.backend.entity.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
// TODO: create unit test case for createWorkspace()
public class WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMemberRepository channelMemberRepository;
    private final WorkspaceMemberRepository workspaceMemberRepository;

    public void createWorkspace(WorkspaceRequestDto workspaceRequestDto) throws Exception {
        User owner = generateOwner(workspaceRequestDto);
        Workspace workspace = generateWorkspace(workspaceRequestDto);
        WorkspaceMember workspaceMember = generateWorkspaceMember(owner, workspace);
        Channel channel = generateChannel(workspace);
        ChannelMember channelMember = generateChannelMember(owner, channel);
    }

    public List<WorkspaceMemberResponseDto> getWorkspaceMemberByUrl(String url) {
        return workspaceMemberRepository.findWorkspaceMembersByUrl(url);
    }

    private User generateOwner(WorkspaceRequestDto workspaceRequestDto) throws Exception {
        Optional<User> ownerOptional = userRepository.findById(workspaceRequestDto.getUserId());

        if (ownerOptional.isEmpty()) {
            throw new Exception("OWNER_NOT_FOUND");
        }

        return ownerOptional.get();
    }

    private Workspace generateWorkspace(WorkspaceRequestDto workspaceRequestDto) {
        Workspace workspace = new Workspace();
        workspace.setName(workspaceRequestDto.getName());
        workspace.setUrl(workspaceRequestDto.getUrl());
        workspaceRepository.save(workspace);
        return workspace;
    }

    private WorkspaceMember generateWorkspaceMember(User owner, Workspace workspace) {
        WorkspaceMember workspaceMember = new WorkspaceMember();
        workspaceMember.saveUser(owner);
        workspaceMember.saveWorkspace(workspace);
        workspaceMemberRepository.save(workspaceMember);

        return workspaceMember;
    }

    private Channel generateChannel(Workspace workspace) {
        Channel channel = new Channel();
        // TODO: create static class or enum class
        channel.setName("GENERAL");
        channel.saveWorkspace(workspace);
        channelRepository.save(channel);

        return channel;
    }

    private ChannelMember generateChannelMember(User owner, Channel channel) {
        ChannelMember channelMember = new ChannelMember();
        channelMember.saveUser(owner);
        channelMember.saveChannel(channel);
        channelMemberRepository.save(channelMember);

        return channelMember;
    }
}

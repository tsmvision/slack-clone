package com.example.backend.service;

import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.example.backend.entity.*;
import com.example.backend.enums.ChannelName;
import com.example.backend.exception.GeneralExceptions;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: create unit test with @DataJpaTest
@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {
    private final WorkspaceMemberRepository workspaceMemberRepository;
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;
    private final ChannelMemberRepository channelMemberRepository;

    public List<WorkspaceMemberResponseDto> getWorkspaceMembers() {
        return workspaceMemberRepository.findWorkspaceMembers();
    }

    // TODO: return created workspaceMember object
    public void createWorkspaceMember(String url, String email) throws Exception {
        Workspace workspace = generateWorkspace(url);
        User user = generateUser(email);
        generateWorkspaceMember(workspace, user);
        generateChannelMember(user);
    }

    private Workspace generateWorkspace(String url) throws Exception {
        Optional<Workspace> workspaceOptional = workspaceRepository.findByUrl(url);
        if (workspaceOptional.isEmpty()) {
            GeneralExceptions.workspaceNotFound();
        }
        return workspaceOptional.get();
    }

    private User generateUser(String email) throws Exception {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            GeneralExceptions.userNotFound();
        }
        return userOptional.get();
    }

    private WorkspaceMember generateWorkspaceMember(Workspace workspace, User user) {
        WorkspaceMember workspaceMember = new WorkspaceMember();
        workspaceMember.saveWorkspace(workspace);
        workspaceMember.saveUser(user);
        workspaceMemberRepository.save(workspaceMember);

        return workspaceMember;
    }

    private ChannelMember generateChannelMember(User user) throws Exception {
        ChannelMember channelMember = new ChannelMember();
        Optional<Channel> channelOptional = channelRepository.findByName(ChannelName.generalChannel);

        if (channelOptional.isEmpty()) {
            GeneralExceptions.channelNotFound();
        }
        Channel channel = channelOptional.get();

        channelMember.saveChannel(channel);
        channelMember.saveUser(user);
        channelMemberRepository.save(channelMember);

        return channelMember;
    }
}

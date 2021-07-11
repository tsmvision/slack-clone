package com.example.backend.service;

import com.example.backend.dto.WorkspaceMemberDto;
import com.example.backend.dto.WorkspaceMemberResponseDto;
import com.example.backend.repository.WorkspaceMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceMemberService {
    private final WorkspaceMemberRepository workspaceMemberRepository;

    public List<WorkspaceMemberResponseDto> getWorkspaceMembers() {
        return workspaceMemberRepository.findWorkspaceMembers();
    }

    public void createWorkspaceMember(String url, String email) {
        // find workspace using url
        // throw exception if workspace not found

        // find user using email
        // throw exception if user not found

        // create new workspaceMember
        //  add workspace
        //  add user
        // save workspaceMember

        // create channelMember
        //  find channel with name == "GENERAL"
        //  add channel above
        //  add user
        // save channelMember
    }

}

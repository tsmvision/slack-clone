package com.example.backend.controller;

import com.example.backend.dto.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/workspaces", produces = MediaType.APPLICATION_JSON_VALUE)
public class WorkspaceController {

    @GetMapping
    public ResponseEntity<String> getWorkspaces(@RequestBody WorkspaceDto workspaceDto) {
        return new ResponseEntity<>("Workspaces!!!", HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<WorkspaceDto> createWorkspaces() {
        return new ResponseEntity<>(new WorkspaceDto(), HttpStatus.OK);
    }

    @GetMapping("/{workspace}/channels")
    public ResponseEntity<ChannelDto> getChannelsInWorkspace(@PathVariable WorkspaceDto workspace) {
        return new ResponseEntity<>(new ChannelDto(), HttpStatus.OK);
    }

    @PostMapping("/{workspace}/channels")
    public ResponseEntity<ChannelDto> createChannelInWorkspace(@PathVariable WorkspaceDto workspace, @RequestBody WorkspaceDto workspaceDto) {
        return new ResponseEntity<>(new ChannelDto(), HttpStatus.OK);
    }

    @PostMapping("/{workspace}/channels/{channel}/chats")
    public ResponseEntity<ChatDto> getChatsInChannel(
            @PathVariable String workspace, @PathVariable String channel, @RequestBody ChannelDto channelDto
            ) {
        return new ResponseEntity<>(new ChatDto(), HttpStatus.OK);
    }

    @GetMapping("/{workspace}/dms/{id}/chats")
    public ResponseEntity<DmDto> getDmById(@PathVariable String workspace, @PathVariable String id) {
        return new ResponseEntity<>(new DmDto(), HttpStatus.OK);
    }

    @GetMapping("/{workspace}/channels/{channel}/members")
    public ResponseEntity<MemberDto> getUsersInChannel(@PathVariable String workspace, @PathVariable String channel) {
        return new ResponseEntity<>(new MemberDto(), HttpStatus.OK);
    }

    @PostMapping("/{workspace}/channels/{channel}/members")
    public ResponseEntity<UserInviteDto> inviteMemberToChannel(
            @PathVariable String workspace, @PathVariable String channel, @RequestBody UserInviteDto userInviteDto) {
        return new ResponseEntity<>(new UserInviteDto(), HttpStatus.OK);
    }

    @GetMapping("/workspaces/{workspace}/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
    }
}

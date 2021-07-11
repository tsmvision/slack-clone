package com.example.backend.service;

import com.example.backend.entity.DirectMessage;
import com.example.backend.entity.User;
import com.example.backend.entity.Workspace;
import com.example.backend.exception.GeneralExceptions;
import com.example.backend.repository.DirectMessageRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DirectMessageService {
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final DirectMessageRepository directMessageRepository;

    // TODO: double-check save() and saveAndFlush()
    public void createWorkspaceDirectMessageChats(
            String url, String content, Long senderId, Long receiverId
    ) throws Exception {
        Workspace worpspace = generateWorkspaceByUrl(url);
        User receiver = generateUser(receiverId);
        User sender = generateUser(senderId);

        DirectMessage dm = new DirectMessage();
        dm.saveReceiver(receiver);
        dm.saveSender(sender);
        dm.setContent(content);
        dm.saveWorkspace(worpspace);

        DirectMessage savedDm = directMessageRepository.saveAndFlush(dm);
        // TODO: setup socket id
        // TODO: socket connect
    }

    private Workspace generateWorkspaceByUrl(String url) throws Exception {
        Optional<Workspace> workspaceOptional = workspaceRepository.findByUrl(url);
        if (workspaceOptional.isEmpty()) {
            GeneralExceptions.workspaceNotFound();
        }
        return workspaceOptional.get();
    }

    private User generateUser(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);

        if (userOptional.isEmpty()) {
            GeneralExceptions.userNotFound();
        }

        return userOptional.get();
    }

    public void createWorkspaceDirectMessageImages(
            String url, File[] files, Long senderId, Long receiverId
    ) throws Exception {
        Workspace workspace = generateWorkspaceByUrl(url);
        User receiver = generateUser(receiverId);
        User sender = generateUser(senderId);

        for (int i = 0; i < files.length; i++) {
            DirectMessage dm = new DirectMessage();
            dm.saveWorkspace(workspace);
            dm.saveSender(sender);
            dm.saveReceiver(receiver);
            DirectMessage savedDm = directMessageRepository.saveAndFlush(dm);
            Optional<DirectMessage> dmWithSender = directMessageRepository.findById(savedDm.getId());
            // TODO: find file handling here!!!
        }
    }
}

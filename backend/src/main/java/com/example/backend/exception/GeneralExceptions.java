package com.example.backend.exception;

import javassist.NotFoundException;

public class GeneralExceptions {
    public static void userNotFound() throws Exception {
        throw new NotFoundException("USER_NOT_FOUND");
    }

    public static void workspaceNotFound() throws Exception {
        throw new NotFoundException("WORKSPACE_NOT_FOUND");
    }

    public static void channelNotFound() throws Exception {
        throw new NotFoundException("CHANNEL_NOT_FOUND");
    }
}

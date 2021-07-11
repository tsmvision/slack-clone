package com.example.backend.exception;

public class GeneralExceptions {
    public static void userNotFound() throws Exception {
        throw new Exception("USER_NOT_FOUND");
    }

    public static void workspaceNotFound() throws Exception {
        throw new Exception("WORKSPACE_NOT_FOUND");
    }

    public static void channelNotFound() throws Exception {
        throw new Exception("CHANNEL_NOT_FOUND");
    }
}

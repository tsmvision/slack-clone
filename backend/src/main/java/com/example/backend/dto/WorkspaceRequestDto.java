package com.example.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkspaceRequestDto {
    private String name;
    private String url;
    private Long userId;
}

package com.example.backend.dto;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessageDto {
    private String timestamp;
    private Integer status;
    private Map<String, String> fields = new HashMap<>();
    private String message;
    private String path;
}

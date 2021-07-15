package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponseDto {
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}

package com.example.backend.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/file")
public class FileController {

    @Value("${file.uploaddir}")
    private String FILE_FOLDER;;

    @GetMapping
    public ResponseEntity<String> upload() {
        return new ResponseEntity<>("upload", HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> singleFileUpload(@RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return "redirect:uploadStatus";
//        }

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(FILE_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);
            // TODO: save path to the database
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>("upload success!!!", HttpStatus.OK);
    }

    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity downloadFileFromLocal(@PathVariable String fileName) {
        Path path = Paths.get(FILE_FOLDER + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

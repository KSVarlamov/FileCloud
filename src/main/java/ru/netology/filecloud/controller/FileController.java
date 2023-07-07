package ru.netology.filecloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.netology.filecloud.service.FileService;

@RestController
@RequestMapping("/")
@CrossOrigin
@Slf4j
public class FileController {

    private final FileService service;

    public FileController(FileService service) {
        this.service = service;
    }

    @GetMapping("/list")
    ResponseEntity<?> getFileList(@RequestParam int limit, @AuthenticationPrincipal UserDetails userDetails) {
        var list = service.getUsersFiles(userDetails.getUsername(), limit);
        var response = ResponseEntity.ok().body(list);
        return response;
    }
}

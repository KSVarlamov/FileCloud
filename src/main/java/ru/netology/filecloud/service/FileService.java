package ru.netology.filecloud.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.netology.filecloud.dto.FileInfo;
import ru.netology.filecloud.dto.converters.UserFileToFileInfoConverter;
import ru.netology.filecloud.repository.FileRepository;
import ru.netology.filecloud.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    private final UserRepository userRepository;
    private final FileRepository fileRepository;

    public FileService(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    public List<FileInfo> getUsersFiles(String username, int limit) {
        var user = userRepository.findByEmail(username);
        var id = user.orElseThrow(() -> {
                    throw new UsernameNotFoundException(username);
                })
                .getId();

         return fileRepository.findByUser_Id(id).stream().limit(limit).map(UserFileToFileInfoConverter::convert).collect(Collectors.toList());
    }
}

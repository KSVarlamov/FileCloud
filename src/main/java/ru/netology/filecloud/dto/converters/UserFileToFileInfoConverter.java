package ru.netology.filecloud.dto.converters;

import ru.netology.filecloud.dto.FileInfo;
import ru.netology.filecloud.model.UserFile;


public class UserFileToFileInfoConverter {
    public static FileInfo convert(UserFile userFile) {
        return new FileInfo(
                userFile.getFileName(),
                userFile.getSize()
        );
    }
}


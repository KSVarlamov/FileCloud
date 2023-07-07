package ru.netology.filecloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.netology.filecloud.model.UserFile;

import java.util.List;

public interface FileRepository extends JpaRepository<UserFile, Long> {
    List<UserFile> findByUser_Id(long id);
}


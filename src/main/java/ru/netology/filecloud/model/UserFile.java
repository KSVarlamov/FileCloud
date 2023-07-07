package ru.netology.filecloud.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "files")
@Data
public class UserFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    @NotBlank
    private String fileName;

    @Column
    @NotBlank
    private String path;

    @Column
    @NotBlank
    private long size;
}

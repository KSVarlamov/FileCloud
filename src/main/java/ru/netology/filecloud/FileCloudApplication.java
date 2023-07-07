package ru.netology.filecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableWebSecurity(debug = true)
public class FileCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileCloudApplication.class, args);

    }

}

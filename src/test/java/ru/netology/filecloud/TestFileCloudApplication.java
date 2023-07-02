package ru.netology.filecloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFileCloudApplication {

    public static void main(String[] args) {
        SpringApplication.from(FileCloudApplication::main).with(TestFileCloudApplication.class).run(args);
    }

}

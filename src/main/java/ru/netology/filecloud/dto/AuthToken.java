package ru.netology.filecloud.dto;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthToken {
    @JsonSetter("auth-token")
    String token;
}

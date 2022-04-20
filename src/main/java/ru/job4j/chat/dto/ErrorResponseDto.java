package ru.job4j.chat.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponseDto {

    private String status;

    private String msg;

    private String type;

}

package ru.job4j.chat.controller;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.job4j.chat.dto.ErrorResponseDto;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    private final ObjectMapper objectMapper;

    @ExceptionHandler(value = {NullPointerException.class})
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ErrorResponseDto.builder()
                                        .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                                        .msg(e.getMessage())
                                        .type(e.getClass().toString())
                                        .build()
                )
        );
        log.error(e.getLocalizedMessage());
    }

}
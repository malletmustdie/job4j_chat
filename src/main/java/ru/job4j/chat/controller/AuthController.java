package ru.job4j.chat.controller;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.dto.AuthRequestDto;
import ru.job4j.chat.dto.AuthResponseDto;
import ru.job4j.chat.dto.ErrorResponseDto;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.security.jwt.JwtTokenProvider;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.util.ApiPathConstants;

@Slf4j
@Api(tags = "Эндпоинт для получения токена пользователя")
@RequiredArgsConstructor
@RestController
@RequestMapping(ApiPathConstants.API_V_1 + ApiPathConstants.AUTHENTICATION)
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final PersonService personService;

    private final ObjectMapper objectMapper;

    @ApiOperation("Авторизация на сервере")
    @PostMapping(value = ApiPathConstants.LOGIN, produces = "application/json")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, requestDto.getPassword())
            );
            Person person = personService.findByUsername(username);
            if (person == null) {
                throw new UsernameNotFoundException(
                        "Person with username " + username + " not found"
                );
            }
            String token = jwtTokenProvider.createToken(username, person.getRoles());
            AuthResponseDto response = AuthResponseDto.builder()
                                                      .username(username)
                                                      .token(token)
                                                      .build();
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class, BadCredentialsException.class})
    public void exceptionHandler(Exception e, HttpServletResponse response)
            throws IOException {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json");
        response.getWriter().write(
                objectMapper.writeValueAsString(
                        ErrorResponseDto.builder()
                                        .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                        .msg(e.getMessage())
                                        .type(e.getClass().toString())
                                        .build()
                )
        );
        log.error(e.getLocalizedMessage());
    }

}

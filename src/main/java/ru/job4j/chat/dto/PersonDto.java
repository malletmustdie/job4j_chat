package ru.job4j.chat.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.model.Role;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long id;

    private String username;

    private String password;

    private List<Role> roles;

}

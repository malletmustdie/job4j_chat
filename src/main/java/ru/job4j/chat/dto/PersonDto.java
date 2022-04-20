package ru.job4j.chat.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.model.Role;
import ru.job4j.chat.util.Operation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    @NotNull(message = "Id must be non null",
             groups = {Operation.OnCreate.class, Operation.OnUpdate.class}
    )
    private Long id;

    @NotBlank(message = "Username must be not empty")
    private String username;

    @NotBlank(message = "Password must be not empty")
    private String password;

    @NotNull
    @NotEmpty
    private List<Role> roles;

}

package ru.job4j.chat.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.util.Operation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

    @NotBlank(message = "Name must be not empty",
              groups = {Operation.OnCreate.class}
    )
    private String name;

}

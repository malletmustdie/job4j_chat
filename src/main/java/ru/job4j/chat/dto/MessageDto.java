package ru.job4j.chat.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.job4j.chat.util.Operation;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @NotNull(message = "Id must be non null",
             groups = {Operation.OnCreate.class, Operation.OnUpdate.class}
    )
    private Long id;

    @NotBlank(message = "Text must be not empty")
    private String text;

    @NotNull(message = "Created must be non null")
    private Instant created;

    @NotNull(message = "PersonId must be non null")
    private Long personId;

    @NotNull(message = "RoomId must be non null")
    private Long roomId;

}

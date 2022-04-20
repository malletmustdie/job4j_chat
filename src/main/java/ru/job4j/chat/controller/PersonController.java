package ru.job4j.chat.controller;

import javax.validation.Valid;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.chat.dto.PersonDto;
import ru.job4j.chat.dto.PersonInfo;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.service.PersonService;
import ru.job4j.chat.util.ApiPathConstants;
import ru.job4j.chat.util.Operation;

@Api(tags = "Эндпоинты для управления пользователями")
@RestController
@RequestMapping(ApiPathConstants.API_V_1 + ApiPathConstants.PERSON)
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @ApiOperation("Создание пользователя")
    @Validated(Operation.OnCreate.class)
    @PostMapping(value = ApiPathConstants.CREATE_PERSON, produces = "application/json")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody PersonDto personDto) {
        return personService.savePerson(personDto);
    }

    @ApiOperation("Редактирование пользователя")
    @Validated(Operation.OnUpdate.class)
    @PutMapping(value = ApiPathConstants.UPDATE_PERSON, produces = "application/json")
    public ResponseEntity<Void> updatePerson(@Valid @RequestBody PersonDto personDto) {
        return personService.updatePerson(personDto);
    }

    @ApiOperation("Удаление пользователя")
    @DeleteMapping(value = ApiPathConstants.DELETE_PERSON,
                   produces = "application/json")
    public ResponseEntity<Void> deletePerson(@RequestParam("personId") Long id) {
        return personService.deletePerson(id);
    }

    @ApiOperation("Получение сущности Person по идентификатору")
    @GetMapping(value = ApiPathConstants.FIND_PERSON,
                produces = "application/json")
    public ResponseEntity<PersonInfo> findPersonById(@RequestParam("personId") Long id) {
        return personService.findPersonById(id);
    }

    @ApiOperation("Получение списка пользователей")
    @GetMapping(ApiPathConstants.FIND_ALL_PERSONS)
    public ResponseEntity<List<PersonInfo>> findAllPersons() {
        return personService.findAllPersons();
    }

}

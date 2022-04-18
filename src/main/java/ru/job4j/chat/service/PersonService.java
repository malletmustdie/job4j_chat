package ru.job4j.chat.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import ru.job4j.chat.dto.PersonDto;
import ru.job4j.chat.dto.PersonInfo;
import ru.job4j.chat.model.Person;

public interface PersonService {

    ResponseEntity<Person> savePerson(PersonDto dto);

    ResponseEntity<Void> updatePerson(PersonDto dto);

    ResponseEntity<Void> deletePerson(Long personId);

    ResponseEntity<PersonInfo> findPersonById(Long personId);

    ResponseEntity<List<PersonInfo>> findAllPersons();

}

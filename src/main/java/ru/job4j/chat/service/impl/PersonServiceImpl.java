package ru.job4j.chat.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.chat.dto.PersonDto;
import ru.job4j.chat.dto.PersonInfo;
import ru.job4j.chat.mapper.PersonMapper;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.repository.PersonRepository;
import ru.job4j.chat.service.PersonService;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    @Transactional
    @Override
    public ResponseEntity<Person> savePerson(PersonDto dto) {
        var person = personMapper.map(dto);
        var result = personRepository.save(person);
        return ResponseEntity.ok(result);
    }

    @Transactional
    @Override
    public ResponseEntity<Void> updatePerson(PersonDto dto) {
        var person = personMapper.map(dto);
        personRepository.findById(person.getId())
                        .ifPresent(personFromDb -> {
                            personFromDb.setUsername(person.getUsername());
                            personFromDb.setPassword(person.getPassword());
                            personFromDb.setRole(person.getRole());
                            person.getRooms().forEach(personFromDb::addRoom);
                            person.getMessages().forEach(personFromDb::addMessage);
                            personRepository.save(personFromDb);
                        });
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<Void> deletePerson(Long personId) {
        personRepository.findById(personId)
                        .ifPresent(personRepository::delete);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @Override
    public ResponseEntity<PersonInfo> findPersonById(Long personId) {
        return ResponseEntity.ok(
                personMapper.toPersonInfo(personRepository.getById(personId))
        );
    }

    @Transactional
    @Override
    public ResponseEntity<List<PersonInfo>> findAllPersons() {
        return ResponseEntity.ok(
                personRepository.findAll()
                                .stream()
                                .map(personMapper::toPersonInfo)
                                .collect(Collectors.toList())
        );
    }

}

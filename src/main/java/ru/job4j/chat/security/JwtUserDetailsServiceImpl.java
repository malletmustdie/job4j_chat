package ru.job4j.chat.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.security.jwt.JwtUser;
import ru.job4j.chat.security.jwt.JwtUserFactory;
import ru.job4j.chat.service.PersonService;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.findByUsername(username);
        if (person == null) {
            throw new UsernameNotFoundException("Person with username: " + username + " not found");
        }
        JwtUser jwtUser = JwtUserFactory.create(person);
        log.info("loadUserByUsername - person with username: {} successfully loaded", username);
        return jwtUser;
    }

}

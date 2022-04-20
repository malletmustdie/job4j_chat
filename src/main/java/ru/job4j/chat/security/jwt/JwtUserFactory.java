package ru.job4j.chat.security.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.job4j.chat.model.Person;
import ru.job4j.chat.model.Role;

@UtilityClass
public final class JwtUserFactory {

    public static JwtUser create(Person person) {
        return new JwtUser(
                person.getId(),
                person.getUsername(),
                person.getPassword(),
                true,
                mapToGrantedAuthorities(new ArrayList<>(person.getRoles()))
        );
    }

    public static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> personRoles) {
        return personRoles.stream()
                          .map(role -> new SimpleGrantedAuthority(role.getName()))
                          .collect(Collectors.toList());
    }

}

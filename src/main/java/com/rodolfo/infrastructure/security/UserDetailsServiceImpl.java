package com.rodolfo.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rodolfo.infrastructure.entity.Person;
import com.rodolfo.infrastructure.repository.PersonRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Person person = personRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found."));

        return org.springframework.security.core.userdetails.User
                .withUsername(person.getEmail())
                .password(person.getPassword())
                .build();
    }
}

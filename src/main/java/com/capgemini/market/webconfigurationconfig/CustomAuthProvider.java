package com.capgemini.market.webconfigurationconfig;

import com.capgemini.market.model.Person;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public CustomAuthProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("innn authenticate");
        String username = authentication.getName();
        Person person = (Person) userDetailsService.loadUserByUsername(authentication.getName());
        String password = String.valueOf(authentication.getCredentials());
        System.out.println(username + " " + password);
        if ((person.getUsername()).equals(username) &&
                (person.getPassword()).equals(password)) {
            var token = new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
            System.out.println("token " + token);
            return token;
        } else {
            throw new AuthenticationCredentialsNotFoundException("Error in authentication!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authentication);
    }
}

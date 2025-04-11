package com.corelia.config;

import com.corelia.error.BadRequestException;
import com.corelia.model.User;
import com.corelia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@AllArgsConstructor
public class EmailAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        Optional<User> user = userService.getByEmail(email);

        if(user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
           return new UsernamePasswordAuthenticationToken(user.get(), password , user.get().getAuthorities());
        }

        throw new BadRequestException("Login data is incorrect");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}

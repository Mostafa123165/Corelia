package com.corelia.service;


import com.corelia.config.JwtGenerator;
import com.corelia.dto.LoginRequestDto;
import com.corelia.dto.LoginResponseDto;
import com.corelia.dto.UserDto;
import com.corelia.error.BadRequestException;
import com.corelia.mapper.UserMapper;
import com.corelia.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtGenerator jwtGenerator;
    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public String register(UserDto userDto) {

        checkDuplicateEmailOrPhone(userDto.getEmail());
        userDto.setPassword(hashingPassword(userDto.getPassword()));
        User user = userMapper.unMap(userDto);
        user.addContact(user.getContacts());
        userService.insert(user);

        return "User registered successfully";
    }


    private void checkDuplicateEmailOrPhone(String email) {
        if(userService.getByEmail(email).isPresent()) {
            throw new BadRequestException("Email already in use");
        }
    }

    private String hashingPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        Authentication authenticated = authenticationManager.authenticate(authentication);

        User user = (User)authenticated.getPrincipal();

        checkUserIsEnabled(user);

        String token = jwtGenerator.generateToken(user.getId(),false);
        UserDto userDto = userMapper.map(user);

        return LoginResponseDto
                .builder()
                .token(token)
                .user(userDto)
                .build();
    }

    private void checkUserIsEnabled(User user) {
        if(!user.isEnabled()) {
            throw new BadRequestException("Your account is not enabled");
        }
    }

}

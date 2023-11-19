package com.example.test.app.service;

import com.example.test.app.exception.DataNotFoundException;
import com.example.test.app.model.auth.LoginRequestDto;
import com.example.test.app.model.auth.LoginResponseDto;
import com.example.test.app.model.auth.RegisterDto;
import com.example.test.app.model.auth.Role;
import com.example.test.app.model.enums.Roles;
import com.example.test.app.model.user.ApplicationUser;
import com.example.test.app.model.user.UserResponseDto;
import com.example.test.app.repository.RoleRepository;
import com.example.test.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private final ModelMapper mapper;

    public UserResponseDto registerUser(RegisterDto registerDto) {
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        Role userRole = roleRepository.findByAuthority(Roles.USER.toString())
                .orElseThrow(() -> new DataNotFoundException("Role " + Roles.USER + " was not found"));
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        ApplicationUser applicationUser = mapper.map(registerDto, ApplicationUser.class);
        applicationUser.setAuthorities(authorities);
        applicationUser.setPassword(encodedPassword);
        return mapper.map(userRepository.save(applicationUser), UserResponseDto.class);
    }

    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword()));
        String token = tokenService.generateJwt(auth);
        return new LoginResponseDto(mapper.map(userRepository.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new DataNotFoundException("User with username=" + loginRequestDto.getUsername() + " was not found")), UserResponseDto.class), token);
    }

}

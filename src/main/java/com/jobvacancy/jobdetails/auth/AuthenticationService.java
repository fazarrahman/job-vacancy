package com.jobvacancy.jobdetails.auth;

import com.jobvacancy.jobdetails.config.JwtService;
import com.jobvacancy.jobdetails.entity.Role;
import com.jobvacancy.jobdetails.entity.Users;
import com.jobvacancy.jobdetails.exception.BadRequestException;
import com.jobvacancy.jobdetails.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        if (Strings.isBlank(request.getUsername())) {
            throw new BadRequestException("Username is required");
        } else if (Strings.isBlank(request.getPassword())) {
            throw new BadRequestException("Password is required");
        } else if (Strings.isBlank(request.getEmail())) {
            throw new BadRequestException("Email is required");
        }

        var user = Users.builder()
                .username(request.getUsername())
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        if (Strings.isBlank(request.getUsername())) {
            throw new BadRequestException("Username is required");
        } else if (Strings.isBlank(request.getPassword())) {
            throw new BadRequestException("Password is required");
        }
        
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getUsername(),
                    request.getPassword()
            )
        );
        var user = repository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

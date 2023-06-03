package com.generation.application.auth;

import com.generation.application.dto.UserCreateUpdateDto;
import com.generation.application.dto.UserReadDto;
import com.generation.application.jwt.JwtService;
import com.generation.application.entity.User;
import com.generation.application.model.Role;
import com.generation.application.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDto register(RegisterRequestDto request) {
        var user = User.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .login(request.login())
                .phoneNumber(request.phoneNumber())
                .password(passwordEncoder.encode(request.password()))
                .role(Role.USER)
                .balance(request.balance())
                .build();
        userService.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }

    public AuthenticationResponseDto authenticate(AuthenticationRequestDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.login(),
                        request.password()
                )
        );
        var user = userService.findByLogin(request.login());
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDto(jwtToken);
    }

    public String checkRole(@NonNull HttpServletRequest request) {
        final String userLogin = getUserLogin(request);
        var user = userService.findByLogin(userLogin);
        return user.getRole().name();
    }

    public String getUserLogin(@NonNull HttpServletRequest request){
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        jwt = authHeader.substring(7);
        return jwtService.extractLogin(jwt);
    }

    public UserReadDto registrationEmployee(UserCreateUpdateDto employee){
        User user = User.builder()
                .login(employee.login())
                .firstName(employee.firstName())
                .lastName(employee.lastName())
                .password(passwordEncoder.encode(employee.password()))
                .role(Role.valueOf(employee.role()))
                .phoneNumber(employee.phoneNumber()).build();
        return userService.save(user);
    }
}

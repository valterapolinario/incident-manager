package com.br.manager.controller;

import com.br.manager.controller.api.AuthApi;
import com.br.manager.dto.auth.AuthRequestDTO;
import com.br.manager.dto.auth.AuthResponseDTO;
import com.br.manager.utils.jwt.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final UserDetailsService userDetailsService;

    @Autowired
    private final JWTUtils jwtUtils;

    @Override
    public ResponseEntity<AuthResponseDTO> login(AuthRequestDTO request) {
        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(request.login(), request.password());
        Authentication authentication = authenticationManager.authenticate(loginToken);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.login());
        final String jwt = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponseDTO(jwt));
    }

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthRequestDTO request) {
//        UsernamePasswordAuthenticationToken loginToken = new UsernamePasswordAuthenticationToken(request.email(), request.password());
//        Authentication authentication = authenticationManager.authenticate(loginToken);
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.email());
//        final String jwt = jwtUtils.generateToken(userDetails);
//        return ResponseEntity.ok(new AuthResponseDTO(jwt));
//    }
}

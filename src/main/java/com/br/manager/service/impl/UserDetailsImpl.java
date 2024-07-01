package com.br.manager.service.impl;

import com.br.manager.exception.GeneralNotFoundException;
import com.br.manager.model.UserDB;
import com.br.manager.repository.UserRepository;
import com.br.manager.utils.message.ErrorMessagesEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserDB userDB = userRepository.findByEmail(login).orElseThrow(() -> new GeneralNotFoundException(ErrorMessagesEnum.LOGIN_INCORRECT.getMessage(), login));
        return User.builder()
                .username(userDB.getEmail())
                .password(userDB.getPassword())
                .authorities(userDB.getAuthorities())
                .build();
    }

}

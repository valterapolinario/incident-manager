package com.br.manager.utils.db;

import com.br.manager.model.UserDB;
import com.br.manager.model.UserPositionEnum;
import com.br.manager.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInitValuesUtils {

    public static final String EMAIL_DEFAULT = "emaildeault@gmail.com";
    public static final String NAME = "userDefault";
    public static final String PASSWORD = "admin123";
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        if (repository.findByEmail(EMAIL_DEFAULT).isEmpty()) {
            UserDB adminUser = new UserDB();
            adminUser.setName(NAME);
            adminUser.setPassword(passwordEncoder.encode(PASSWORD));
            adminUser.setPosition(UserPositionEnum.ADMIN);
            adminUser.setEmail(EMAIL_DEFAULT);
            repository.save(adminUser);
        }
    }
}

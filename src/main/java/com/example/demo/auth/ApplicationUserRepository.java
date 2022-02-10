package com.example.demo.auth;

import com.example.demo.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ApplicationUserRepository implements IApplicationUserRepository {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<ApplicationUser> loadUserByUsername(String username) {
        return getUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getUsers() {
        List<ApplicationUser> users = List.of(
                new ApplicationUser(
                        Role.STUDENT.getGrantedAuthority(),
                        "anna",
                        passwordEncoder.encode("anna"),
                        true, true,
                        true, true
                ),
                new ApplicationUser(
                        Role.ADMIN.getGrantedAuthority(),
                        "alex",
                        passwordEncoder.encode("alex"),
                        true, true,
                        true, true
                ),
                new ApplicationUser(
                        Role.ADMINTRAINEE.getGrantedAuthority(),
                        "tom",
                        passwordEncoder.encode("tom"),
                        true, true,
                        true, true
                )
        );
        return users;
    }
}

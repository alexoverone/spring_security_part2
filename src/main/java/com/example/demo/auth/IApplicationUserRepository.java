package com.example.demo.auth;

import java.util.Optional;

public interface IApplicationUserRepository {
    Optional<ApplicationUser> loadUserByUsername(String username);
}

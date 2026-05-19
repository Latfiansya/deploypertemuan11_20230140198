package com.deploy.pertemuan11.service;

import com.deploy.pertemuan11.model.Profile;
import com.deploy.pertemuan11.model.User;
import com.deploy.pertemuan11.model.dto.RegisterRequest;
import com.deploy.pertemuan11.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void register(RegisterRequest request) {
        // Buat User
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        // Buat Profile dan hubungkan ke User
        Profile profile = Profile.builder()
                .id(UUID.randomUUID().toString())
                .nama(request.getNama())
                .alamat(request.getAlamat())
                .user(user)
                .build();

        // Set profile ke user (bidirectional relationship)
        user.setProfile(profile);

        // Simpan user -> cascade ALL akan otomatis menyimpan profile juga
        userRepository.save(user);
    }

    public User getLoggedInUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String username = authentication.getName();

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User tidak ditemukan"));
    }
}
package com.deploy.pertemuan11.controller;

import com.deploy.pertemuan11.model.dto.RegisterRequest;
import com.deploy.pertemuan11.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("request", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request) {
        authService.register(request);
        return "redirect:/login";
    }

    // Halaman login — deteksi ?error dilakukan di Thymeleaf via ${param.error}
    // sehingga tidak perlu @RequestParam di sini
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
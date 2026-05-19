package com.deploy.pertemuan11.controller;

import com.deploy.pertemuan11.model.Profile;
import com.deploy.pertemuan11.model.User;
import com.deploy.pertemuan11.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AuthService authService;

    @GetMapping("/home")
    public String home(Model model) {
        User user = authService.getLoggedInUser();
        model.addAttribute("user", user);

        // Guard null: pastikan profile tidak null sebelum dikirim ke view
        Profile profile = user.getProfile();
        model.addAttribute("profile", profile != null ? profile : new Profile());

        return "home";
    }
}
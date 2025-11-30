package org.appynov.banking.infrastructure.application.driving.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestTemplateController {

    @GetMapping("/test-login")
    public String testLoginTemplate() {
        return "login"; // Thymeleaf doit résoudre login.html
    }
}

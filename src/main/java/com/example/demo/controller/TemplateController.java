package com.example.demo.controller;

import com.example.demo.auth.ApplicationUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/")
public class TemplateController {

    @GetMapping("login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("courses")
    public String getCoursesPage(HttpSession session) {
        ApplicationUser user = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", user.getUsername());
        return "courses";
    }
}

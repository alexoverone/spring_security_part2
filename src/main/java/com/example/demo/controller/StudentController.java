package com.example.demo.controller;

import com.example.demo.auth.ApplicationUser;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.UserNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = List.of(
            new Student(1, "Oleg Olegovish"),
            new Student(2, "Ivan Ivanov"),
            new Student(3, "Alexander Alexandrovich")
    );
// hasRole('') , hasAnyRole(''), hasAuthority(''), hasAnyAuthority('')
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Student> findAll(HttpSession session) {
        String username = (String) session.getAttribute("username");
        System.out.println(username);
        return STUDENTS;
    }

    @GetMapping(path = "{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMINTRAINEE')")
    public Student findById(@PathVariable("studentId") Integer id) {
        return STUDENTS.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst()
                .orElseThrow(() ->
                        new UserNotFoundException(String.format("User with id = %s not found", id)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void create(@RequestBody Student student) {
        System.out.println("create");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void delete(@PathVariable("studentId") Integer studentId) {
        System.out.println("delete");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void update(@PathVariable("studentId") Integer studentId,
                       @RequestBody Student student) {
        System.out.println("update");
        System.out.println("Update student with id = " + studentId + " . " + student);
    }
}

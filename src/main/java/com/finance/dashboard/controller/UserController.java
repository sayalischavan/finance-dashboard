package com.finance.dashboard.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.finance.dashboard.dto.UserDTO;
import com.finance.dashboard.entity.Role;
import com.finance.dashboard.entity.Status;
import com.finance.dashboard.entity.UserEntity;
import com.finance.dashboard.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createUser")
    public UserEntity create(@Valid @RequestBody UserDTO dto) {

        UserEntity user = new UserEntity();
        user.setName(dto.name);
        user.setEmail(dto.email);
        user.setRole(Role.valueOf(dto.role));
        user.setStatus(Status.ACTIVE);

        return service.createUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAll")
    public List<UserEntity> getAll() {
        return service.getAllUsers();
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity getById(@PathVariable Long id) {
        return service.getUserById(id);
    }
    
    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public UserEntity updateStatus(@PathVariable Long id,
                                   @RequestParam Status status) {
        return service.updateStatus(id, status);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        service.deleteUser(id);
    }
}
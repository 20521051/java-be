package com.backend.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.backend.store.models.Admin;
import com.backend.store.repositories.AdminRepository;

import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @GetMapping("/{id}")
    public Admin getAdminById(@PathVariable String id) {
        return adminRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminRepository.save(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable String id, @RequestBody Admin updatedAdmin) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            admin.setName(updatedAdmin.getName());
            admin.setUsername(updatedAdmin.getUsername());
            admin.setPassword(updatedAdmin.getPassword());
            return adminRepository.save(admin);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable String id) {
        adminRepository.deleteById(id);
    }
}

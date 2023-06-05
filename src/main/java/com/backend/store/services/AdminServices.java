package com.backend.store.services;

import com.backend.store.models.Admin;
import com.backend.store.models.Admin.AdminLoginDTO;
import com.backend.store.models.Admin.IUpdateAdmin;
import com.backend.store.repositories.AdminRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdminServices {
    public AdminRepository adminRepository;

    public AdminServices(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin creatAdmin(String name, String username, String password) {
        try {
            List<Admin> listAdmin = adminRepository.findAll();
            for (Admin admin : listAdmin) {
                if (admin.getUsername() == username)
                    throw new Error("Admin đã tồn tại");
            }
            Admin result = new Admin();
            result.setName(name);
            result.setUsername(username);
            result.setPassword(password);
            return result;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;// Return null or throw an exception based on your requirements
    }

    private static boolean comparePassword(Object password, String password2) {
        return false;
    }

    public String login(AdminLoginDTO adminDTO) {
        try {

            Optional<Admin> optionalAdmin = adminRepository.findByUsername(adminDTO.getUsername());

            if (optionalAdmin.isEmpty()) {
                throw new Error("Admin not found");
            }

            Admin admin = optionalAdmin.get();
            boolean passwordMatch = comparePassword(adminDTO.getPassword(), admin.getPassword());

            if (!passwordMatch) {
                throw new Error("Incorrect password");
            }

            // String token = jwt.sign(
            // new JwtToken(admin.getId(), Arrays.asList("admin")),
            // process.env.JWT_SECRET);

            // return new ResponseEntity<>(token, HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    public static Admin update(IUpdateAdmin dto, String userId) {
        try {
            Admin admin = Admin.findById(userId);

            if (admin == null) {
                throw new Error("Admin not found");
            }

            if (comparePassword(dto.getOldPassword(), admin.getPassword())) {
                if (dto.getName() != null) {
                    admin.setName((String) dto.getName());
                }
                if (dto.getPassword() != null) {
                    admin.setPassword(hashPasswords(dto.getPassword()));
                }
                admin.save();
            } else {
                throw new Error("Password not found");
            }

            Admin resAdmin = new Admin();
            resAdmin.setName(admin.getName());
            resAdmin.setUsername(admin.getUsername());

            return resAdmin;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    private static String hashPasswords(Object password) {
        return null;
    }

    public Admin findAdminById(String adminId) {
        Optional<Admin> adminOptional = adminRepository.findById(adminId);

        if (adminOptional.isEmpty()) {
            throw new Error("Admin không tồn tại");
        }

        Admin admin = adminOptional.get();

        Admin adminRes = new Admin();
        adminRes.setName(admin.getName());
        adminRes.setUsername(admin.getUsername());

        return adminRes;
    }
}
package com.balamut.yejournal.school.service;

import com.balamut.yejournal.authentication.entity.User;
import com.balamut.yejournal.authentication.repository.UserRepository;
import com.balamut.yejournal.school.Admin;
import com.balamut.yejournal.school.repository.AdminRepository;
import com.balamut.yejournal.school.request.RegisterAdminRequest;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public Admin registerAdmin(RegisterAdminRequest request) {
        Admin admin;
        User user;
        return null;
    }
}

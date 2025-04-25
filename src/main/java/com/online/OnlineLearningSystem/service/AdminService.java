package com.online.OnlineLearningSystem.service;

import com.online.OnlineLearningSystem.Model.Admin;
import com.online.OnlineLearningSystem.repository.AdminRepository;

import java.util.List;
import java.util.Optional;

public class AdminService {

    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    public Optional<Admin>findById(Long id) {
        return adminRepository.findById(id);
    }
    public Admin create(Admin admin) {
        return adminRepository.save(admin);
    }

    public boolean update(long id,Admin admin) {
       if(adminRepository.existsById(id)){
         // admin.setId(id);
          return true;
       }
       return false;
    }

    public boolean delete(long id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

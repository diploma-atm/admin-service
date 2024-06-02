package kz.diploma.admin.service.service.admin.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.service.BaseUserService;
import kz.diploma.library.shared.model.entity.AdminEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;

import java.util.Optional;

public abstract class BaseAdminService extends BaseUserService {

    public BaseAdminService(AdminRepository adminRepository, ClientRepository clientRepository) {
        super(adminRepository, clientRepository);
    }

    public static void getParsedAdminEntity(AdminDTO dto, AdminEntity entity){
        entity.post = dto.post;
        entity.name = dto.name;
        entity.lastName = dto.lastName != null ? dto.lastName : "";
        entity.surname = dto.surname;
        entity.phoneNumber = dto.phoneNumber;
        entity.registration = dto.registration;
        entity.password = dto.password;
    }

    public AdminEntity baseGetAdminById(Integer adminId){
        return adminRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin with id " + adminId + " not found"));
    }

    public Optional<AdminEntity> baseGetAdminByPhoneNumber(String phoneNumber){
        return adminRepository.findByPhoneNumber(phoneNumber);
    }
}

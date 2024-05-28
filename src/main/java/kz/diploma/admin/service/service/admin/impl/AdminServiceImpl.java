package kz.diploma.admin.service.service.admin.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.service.admin.AdminService;
import kz.diploma.library.shared.model.entity.AdminEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl extends BaseAdminService implements AdminService {
    private final AdminRepository adminRepository;

    @Override
    public Integer addAdmin(AdminDTO adminDTO) {
        var admin = new AdminEntity();

        admin.surname = adminDTO.surname;
        admin.name = adminDTO.name;
        admin.post = adminDTO.post;
        admin.phoneNumber = adminDTO.phoneNumber;
        admin.registration = adminDTO.registration;

        return adminRepository.save(admin).id;
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        var admin = getById(adminId);
        adminRepository.delete(admin);
    }

    @Override
    public void updateAdmin(AdminDTO adminDTO, Integer adminId) {
        AdminEntity entity = getById(adminId);

        getParsedAdminEntity(adminDTO, entity);

        adminRepository.save(entity);
    }

    @Override
    public AdminEntity getById(Integer adminId){
        return adminRepository.findById(adminId).orElseThrow(() -> new EntityNotFoundException("Admin with this id not found"));
    }

}

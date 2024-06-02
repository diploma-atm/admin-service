package kz.diploma.admin.service.service.admin.impl;

import kz.diploma.admin.service.model.UserAction;
import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.service.admin.AdminService;
import kz.diploma.admin.service.service.admin.impl.save.AdminSaveService;
import kz.diploma.library.shared.model.entity.AdminEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseAdminService implements AdminService {
    private final AdminSaveService adminSaveService;

    public AdminServiceImpl(AdminRepository adminRepository, ClientRepository clientRepository, AdminSaveService adminSaveService) {
        super(adminRepository, clientRepository);
        this.adminSaveService = adminSaveService;
    }

    @Override
    public Integer addAdmin(AdminDTO adminDTO) {
        adminDTO.setAction(UserAction.SAVE);

        return adminSaveService.save(adminDTO);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        var admin = getById(adminId);
        adminRepository.delete(admin);
    }

    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        adminDTO.setAction(UserAction.UPDATE);
        adminSaveService.save(adminDTO);
    }

    @Override
    public AdminEntity getById(Integer adminId){
        return baseGetAdminById(adminId);
    }

}

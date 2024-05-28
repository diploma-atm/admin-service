package kz.diploma.admin.service.service.admin;


import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.library.shared.model.entity.AdminEntity;

public interface AdminService {
    AdminEntity getById(Integer adminId);

    Integer addAdmin(AdminDTO adminDTO);

    void deleteAdmin(Integer adminId);

    void updateAdmin(AdminDTO adminDTO, Integer adminId);
}

package kz.diploma.admin.service.service.impl.subservices.admin.impl;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.library.shared.model.entity.AdminEntity;

public abstract class BaseAdminService {
    public static void getParsedAdminEntity(AdminDTO dto, AdminEntity entity){
        entity.post = dto.post;
        entity.name = dto.name;
        entity.lastName = dto.lastName != null ? dto.lastName : "";
        entity.surname = dto.surname;
        entity.phoneNumber = dto.phoneNumber;
        entity.registration = dto.registration;
    }
}

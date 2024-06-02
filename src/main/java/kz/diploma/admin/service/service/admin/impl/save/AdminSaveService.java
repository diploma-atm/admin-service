package kz.diploma.admin.service.service.admin.impl.save;

import kz.diploma.admin.service.model.UserAction;
import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.service.admin.impl.BaseAdminService;
import kz.diploma.library.shared.model.entity.AdminEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminSaveService extends BaseAdminService {

    public AdminSaveService(AdminRepository adminRepository, ClientRepository clientRepository) {
        super(adminRepository, clientRepository);
    }

    @Transactional
    public Integer save(AdminDTO adminDTO) {
        AdminEntity entityToSave;

        if (adminDTO.getAction() == UserAction.SAVE) {
            checkUserForFreePhoneNumber(adminDTO.phoneNumber);

            entityToSave = new AdminEntity();
        }  else if (adminDTO.getAction() == UserAction.UPDATE) {
            entityToSave = baseGetAdminById(adminDTO.getId());

            if(!entityToSave.phoneNumber.equalsIgnoreCase(adminDTO.phoneNumber)) {
                checkUserForFreePhoneNumber(adminDTO.phoneNumber);
            }

        } else {
            return 0;
        }

        getParsedAdminEntity(adminDTO, entityToSave);

        return adminRepository.save(entityToSave).id;
    }
}

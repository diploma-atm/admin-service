package kz.diploma.admin.service.service.client.impl.save;

import kz.diploma.admin.service.model.UserAction;
import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.admin.service.service.client.impl.BaseClientService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientSaveService extends BaseClientService {

    public ClientSaveService(AdminRepository adminRepository, ClientRepository clientRepository) {
        super(adminRepository, clientRepository);
    }

    public Integer save(ClientRequest clientRequest) {
        ClientEntity entityToSave;

        if (clientRequest.getAction() == UserAction.SAVE) {
            checkUserForFreePhoneNumber(clientRequest.phoneNumber);

            entityToSave = new ClientEntity();
            entityToSave.isBlocked = false;
        }  else if (clientRequest.getAction() == UserAction.UPDATE) {
            entityToSave = baseGetClientById(clientRequest.getId());

            if(!entityToSave.phoneNumber.equalsIgnoreCase(clientRequest.phoneNumber)) {
                checkUserForFreePhoneNumber(clientRequest.phoneNumber);
            }

        } else {
            return 0;
        }

        parseClientDTO2Entity(clientRequest, entityToSave);

        return clientRepository.save(entityToSave).id;
    }

}

package kz.diploma.admin.service.service.client.impl.save;

import jakarta.persistence.EntityExistsException;
import kz.diploma.admin.service.model.ClientAction;
import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.admin.service.service.client.impl.BaseClientService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientSaveService extends BaseClientService {

    public ClientSaveService(ClientRepository clientRepository) {
        super(clientRepository);
    }

    public Integer save(ClientRequest clientRequest) {
        ClientEntity entityToSave;

        if (clientRequest.getAction() == ClientAction.SAVE) {
            checkPhoneNumberForFree(clientRequest.phoneNumber);

            entityToSave = new ClientEntity();

        }  else if (clientRequest.getAction() == ClientAction.UPDATE) {
            entityToSave = baseGetClientById(clientRequest.getId());

            if(!entityToSave.phoneNumber.equalsIgnoreCase(clientRequest.phoneNumber)) {
                checkPhoneNumberForFree(clientRequest.phoneNumber);
            }

        } else {
            return 0;
        }

        parseClientDTO2Entity(clientRequest, entityToSave);

        return clientRepository.save(entityToSave).id;
    }

    private void checkPhoneNumberForFree(String phoneNumber) {
        var clientOpt = baseGetClientByPhoneNumber(phoneNumber);

        if (clientOpt.isPresent()) {
            throw new EntityExistsException("User with this phone number already exists");
        }
    }
}

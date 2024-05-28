package kz.diploma.admin.service.service.client.impl.save;

import jakarta.persistence.EntityExistsException;
import kz.diploma.admin.service.model.ClientAction;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.client.impl.BaseClientService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientSaveService extends BaseClientService {

    public ClientSaveService(ClientRepository clientRepository) {
        super(clientRepository);
    }

    public Integer save(ClientDTO clientDTO) {
        ClientEntity entityToSave;

        if (clientDTO.getAction() == ClientAction.SAVE) {
            checkPhoneNumberForFree(clientDTO.phoneNumber);

            entityToSave = new ClientEntity();

        }  else if (clientDTO.getAction() == ClientAction.UPDATE) {
            entityToSave = baseGetClientById(clientDTO.getId());

            if(!entityToSave.phoneNumber.equalsIgnoreCase(clientDTO.phoneNumber)) {
                checkPhoneNumberForFree(clientDTO.phoneNumber);
            }

        } else {
            return 0;
        }

        parseClientDTO2Entity(clientDTO, entityToSave);

        return clientRepository.save(entityToSave).id;
    }

    private void checkPhoneNumberForFree(String phoneNumber) {
        var clientOpt = baseGetClientByPhoneNumber(phoneNumber);

        if (clientOpt.isPresent()) {
            throw new EntityExistsException("User with this phone number already exists");
        }
    }
}

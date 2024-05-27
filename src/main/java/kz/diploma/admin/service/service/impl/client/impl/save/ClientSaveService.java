package kz.diploma.admin.service.service.impl.client.impl.save;

import jakarta.persistence.EntityExistsException;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.impl.client.impl.BaseClientService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientSaveService extends BaseClientService {

    public ClientSaveService(ClientRepository clientRepository) {
        super(clientRepository);
    }

    public Integer save(ClientDTO clientDTO) {
        var clientOpt = baseGetClientByPhoneNumber(clientDTO.getPhoneNumber());

        if (clientOpt.isPresent()) {
            throw new EntityExistsException("User with this phone number already exists");
        }
        var clientEntity = new ClientEntity();
        parseClientDTO2Entity(clientDTO, clientEntity);

        return clientRepository.save(clientEntity).id;
    }
}

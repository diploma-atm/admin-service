package kz.diploma.admin.service.service.impl.client.impl;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.impl.client.ClientService;
import kz.diploma.admin.service.service.impl.client.impl.save.ClientSaveService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import org.springframework.stereotype.Service;


@Service
public class ClientServiceImpl extends BaseClientService implements ClientService {
    private final ClientSaveService clientSaveService;

    public ClientServiceImpl(ClientRepository clientRepository, ClientSaveService clientSaveService) {
        super(clientRepository);
        this.clientSaveService = clientSaveService;
    }

    @Override
    public void addClient2BlackList(Integer clientId) {
        var client = getClientById(clientId);
        client.isBlocked = true;

        clientRepository.save(client);
    }

    @Override
    public ClientEntity getClientById(Integer clientId) {
        return baseGetClientById(clientId);
    }

    @Override
    public Integer addClient(ClientDTO clientDTO) {
        return clientSaveService.save(clientDTO);
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(ClientDTO clientDTO) {
        var entity = baseGetClientById(clientDTO.id);
        parseClientDTO2Entity(clientDTO, entity);

        clientRepository.save(entity);
    }
}

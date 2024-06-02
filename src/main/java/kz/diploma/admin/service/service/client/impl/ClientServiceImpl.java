package kz.diploma.admin.service.service.client.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.ClientAction;
import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.admin.service.service.client.impl.save.ClientSaveService;
import kz.diploma.admin.service.service.client.ClientService;
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
    public Integer addClient(ClientRequest clientRequest) {
        clientRequest.setAction(ClientAction.SAVE);
        return clientSaveService.save(clientRequest);
    }

    @Override
    public void deleteClient(Integer clientId) {
        var ent = clientRepository.findById(clientId);
        if(ent.isEmpty()) {
            throw new EntityNotFoundException("User with this id not found");
        }

        clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(ClientRequest clientRequest) {
        clientRequest.setAction(ClientAction.UPDATE);
        clientSaveService.save(clientRequest);
    }
}

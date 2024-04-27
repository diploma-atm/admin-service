package kz.diploma.admin.service.service.impl.subservices.client.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.impl.subservices.client.ClientService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseClientService implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public void addClient2BlackList(Integer clientId) {
        var client = getClientById(clientId);
        client.isBlocked = true;

        clientRepository.save(client);
    }

    @Override
    public ClientEntity getClientById(Integer clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client with this id not found"));
    }

    @Override
    public Integer addClient(ClientDTO clientDTO) {
        var clientEntity = new ClientEntity();

        getParsedClientEntity(clientDTO, clientEntity);

        return clientRepository.save(clientEntity).id;
    }

    @Override
    public void deleteClient(Integer clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public void updateClient(ClientDTO clientDTO) {
        var entity = clientRepository.findById(clientDTO.id).orElseThrow(() -> new EntityNotFoundException("Client with this id not found"));
        getParsedClientEntity(clientDTO, entity);

        clientRepository.save(entity);
    }
}

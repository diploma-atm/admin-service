package kz.diploma.admin.service.service.impl.subservices.client.impl;

import jakarta.persistence.EntityNotFoundException;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.impl.subservices.client.ClientService;
import kz.diploma.admin.service.service.impl.subservices.card.ProductService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl extends BaseClientService implements ClientService {
    private final ClientRepository clientRepository;
    private final ProductService productService;

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
        var client = getClientById(clientId);

        clientRepository.delete(client);
    }

    @Override
    public void updateClient(ClientDTO clientDTO, Integer clientId) {
        var client = getClientById(clientId);

        getParsedClientEntity(clientDTO, client);

        clientRepository.save(client);
    }


    @Override
    public ClientEntity getClientByPan(String pan) {
        var product = productService.getByPan(pan);

        return getClientById(product.clientEntity.id);
    }

    @Override
    public List<ClientEntity> getClientByFio(String surname, String name, String lastname) {
        return clientRepository.findClientByFio(surname, name, lastname);
    }

    @Override
    public ClientEntity getClientByPhoneNumber(String phoneNumber) {
        var client = clientRepository.findClientByPhoneNumber(phoneNumber);

        if(client.isPresent()){
            return client.get();
        } else{
            throw new EntityNotFoundException("Client with this phone number not found");
        }
    }
}

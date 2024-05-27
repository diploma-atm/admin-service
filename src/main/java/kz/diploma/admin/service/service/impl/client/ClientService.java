package kz.diploma.admin.service.service.impl.client;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.library.shared.model.entity.ClientEntity;

public interface ClientService {
    void addClient2BlackList(Integer clientId);

    ClientEntity getClientById(Integer clientId);

    Integer addClient(ClientDTO clientDTO);

    void deleteClient(Integer clientId);

    void updateClient(ClientDTO clientDTO);
}

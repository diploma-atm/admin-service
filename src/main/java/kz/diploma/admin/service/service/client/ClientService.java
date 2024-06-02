package kz.diploma.admin.service.service.client;

import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.library.shared.model.entity.ClientEntity;

public interface ClientService {
    void addClient2BlackList(Integer clientId);

    ClientEntity getClientById(Integer clientId);

    Integer addClient(ClientRequest clientRequest);

    void deleteClient(Integer clientId);

    void updateClient(ClientRequest clientRequest);

}

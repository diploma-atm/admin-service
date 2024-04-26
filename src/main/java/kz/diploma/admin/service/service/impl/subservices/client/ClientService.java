package kz.diploma.admin.service.service.impl.subservices.client;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.library.shared.model.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    void addClient2BlackList(Integer clientId);
    ClientEntity getClientById(Integer clientId);
    Integer addClient(ClientDTO clientDTO);
    void deleteClient(Integer clientId);
    void updateClient(ClientDTO clientDTO, Integer clientId);

    ClientEntity getClientByPan(String pan);

    List<ClientEntity> getClientByFio(String surname, String name, String lastname);

    ClientEntity getClientByPhoneNumber(String phoneNumber);
}

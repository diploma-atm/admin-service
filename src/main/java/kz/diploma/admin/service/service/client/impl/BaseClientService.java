package kz.diploma.admin.service.service.client.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.admin.service.service.BaseUserService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;
import java.util.Objects;
import java.util.Optional;

public abstract class BaseClientService extends BaseUserService {

    public BaseClientService(AdminRepository adminRepository, ClientRepository clientRepository) {
        super(adminRepository, clientRepository);
    }

    public static void parseClientDTO2Entity(ClientRequest dto, ClientEntity entity){
        entity.surname = dto.surname;
        entity.name = dto.name;
        entity.phoneNumber = dto.phoneNumber;
        if(!Objects.isNull(dto.lastName)) {
            entity.lastName = dto.lastName;
        }
        entity.password = dto.password;
        entity.isBlocked = dto.isBlocked;
    }

    public ClientEntity baseGetClientById(Integer clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not found"));
    }

    public Optional<ClientEntity> baseGetClientByPhoneNumber(String phoneNumber){
        return clientRepository.findClientByPhoneNumber(phoneNumber);
    }
}

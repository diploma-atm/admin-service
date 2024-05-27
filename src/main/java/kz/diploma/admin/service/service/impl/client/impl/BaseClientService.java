package kz.diploma.admin.service.service.impl.client.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseClientService {
    protected final ClientRepository clientRepository;

    public static void parseClientDTO2Entity(ClientDTO dto, ClientEntity entity){
        entity.surname = dto.surname;
        entity.name = dto.name;
        entity.phoneNumber = dto.phoneNumber;
        if(!Objects.isNull(dto.lastName)) {
            entity.lastName = dto.lastName;
        }
        entity.isBlocked = dto.isBlocked;
    }

    public ClientEntity baseGetClientById(Integer clientId){
        return clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("Client with id " + clientId + " not found"));
    }

    public Optional<ClientEntity> baseGetClientByPhoneNumber(String phoneNumber){
        return clientRepository.findClientByPhoneNumber(phoneNumber);
    }
}

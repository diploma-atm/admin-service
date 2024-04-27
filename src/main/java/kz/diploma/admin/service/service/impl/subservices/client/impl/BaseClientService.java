package kz.diploma.admin.service.service.impl.subservices.client.impl;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.library.shared.model.entity.ClientEntity;

import java.util.Objects;

public abstract class BaseClientService {

    public static void getParsedClientEntity(ClientDTO dto, ClientEntity entity){
        entity.surname = dto.surname;
        entity.name = dto.name;
        entity.phoneNumber = dto.phoneNumber;
        if(!Objects.isNull(dto.lastName)) {
            entity.lastName = dto.lastName;
        }
        entity.isBlocked = dto.isBlocked;
    }
}

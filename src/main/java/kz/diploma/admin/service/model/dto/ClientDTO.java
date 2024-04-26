package kz.diploma.admin.service.model.dto;

import kz.diploma.library.shared.model.entity.ClientEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {
    public Integer id;
    public String surname;
    public String name;
    public String lastName;
    public String phoneNumber;

    public ClientDTO(ClientEntity clientEntity){
        this.id = clientEntity.id;
        this.surname = clientEntity.surname;
        this.name = clientEntity.name;
        if(!Objects.isNull(clientEntity.lastName)){
            this.lastName = clientEntity.lastName;
        }
        this.phoneNumber = clientEntity.phoneNumber;
    }
}

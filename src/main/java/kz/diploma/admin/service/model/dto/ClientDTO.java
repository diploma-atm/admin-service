package kz.diploma.admin.service.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {
    public Integer id;
    public String surname;
    public String name;
    public String lastName;
    public String phoneNumber;
    public Boolean isBlocked;
}

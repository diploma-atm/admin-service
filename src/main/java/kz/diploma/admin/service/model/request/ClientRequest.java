package kz.diploma.admin.service.model.request;

import kz.diploma.admin.service.model.ClientAction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientRequest {
    public Integer id;
    public String surname;
    public String name;
    public String lastName;
    public String phoneNumber;
    public Boolean isBlocked;
    public ClientAction action;
}

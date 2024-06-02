package kz.diploma.admin.service.model.dto;

import kz.diploma.admin.service.model.UserAction;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdminDTO {
    public Integer id;

    public String surname;

    public String name;

    public String lastName;

    public String phoneNumber;

    public String post;

    public String registration;

    public String password;

    public UserAction action;
}

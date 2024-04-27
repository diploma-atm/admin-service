package kz.diploma.admin.service.service;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.model.dto.ProductDTO;

public interface MainService {
    Integer addClient(ClientDTO clientDTO);

    void deleteClient(Integer clientId);

    void updateClient(ClientDTO clientDTO);

    void addClient2BlackList(Integer clientId);

    void addClientProduct(Integer clientId);

    void deleteClientProduct(Integer productId);

    void updateClientProduct(ProductDTO productDTO, Integer clientId);

    void blockProduct(Integer productId);

    Integer addAdmin(AdminDTO adminDTO);

    void deleteAdmin(Integer adminId);

    void updateAdmin(AdminDTO adminDTO, Integer adminId);

}

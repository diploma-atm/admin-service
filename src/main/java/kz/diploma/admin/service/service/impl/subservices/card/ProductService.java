package kz.diploma.admin.service.service.impl.subservices.card;

import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.entity.ProductEntity;

public interface ProductService {
    ProductEntity getById(Integer productId);

    void blockProduct(Integer productId);

    ProductEntity getByPan(String pan);

    void addClientProduct(ClientEntity clientEntity);

    void deleteClientProduct(Integer productId);

    void updateClientProduct(ProductDTO productDTO, Integer productId);
}

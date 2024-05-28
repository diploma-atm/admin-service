package kz.diploma.admin.service.service.card;

import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.model.response.ProductResponse;
import kz.diploma.library.shared.model.entity.ProductEntity;

public interface ProductService {
    ProductEntity getById(Integer productId);

    void blockProduct(Integer productId);

    ProductEntity getByPan(String pan);

    ProductResponse addClientProduct(Integer clientId);

    void deleteClientProduct(Integer productId);

    void updateClientProduct(ProductDTO productDTO, Integer productId);
}

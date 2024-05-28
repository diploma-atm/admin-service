package kz.diploma.admin.service.service.card.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.model.response.ProductResponse;
import kz.diploma.admin.service.service.card.ProductService;
import kz.diploma.admin.service.service.card.impl.save.ProductSaveService;
import kz.diploma.admin.service.service.card.AccountService;
import kz.diploma.admin.service.service.client.ClientService;
import kz.diploma.library.shared.model.entity.ProductEntity;
import kz.diploma.library.shared.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AccountService accountService;
    private final ProductSaveService productSaveService;
    private final ClientService clientService;

    @Override
    public ProductEntity getById(Integer productId){
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product with this id not found"));
    }

    @Override
    public void blockProduct(Integer productId) {
        var product = getById(productId);

        product.isBlocked = true;
        productRepository.save(product);
    }

    @Override
    public ProductEntity getByPan(String pan) {
        return productRepository.findByPan(pan).orElseThrow(() -> new EntityNotFoundException("Product with this id not found"));
    }

    @Override
    public ProductResponse addClientProduct(Integer clientId) {
        var ent = clientService.getClientById(clientId);
        return productSaveService.addClientProduct(ent);
    }

    @Override
    public void deleteClientProduct(Integer productId) {
        var product = getById(productId);

        accountService.deleteAccount(product.account.id);
        productRepository.delete(product);
    }

    @Override
    public void updateClientProduct(ProductDTO productDTO, Integer productId) {
        var product = getById(productId);
        product.pin = productDTO.pin;

        productRepository.save(product);
    }

}

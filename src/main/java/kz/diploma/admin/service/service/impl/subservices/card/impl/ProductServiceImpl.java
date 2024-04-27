package kz.diploma.admin.service.service.impl.subservices.card.impl;

import jakarta.persistence.EntityNotFoundException;
import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.service.impl.subservices.card.AccountService;
import kz.diploma.admin.service.service.impl.subservices.card.ProductService;
import kz.diploma.library.shared.model.entity.ProductEntity;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final AccountService accountService;


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
    public void addClientProduct(ClientEntity clientEntity) {
        var newProduct = ProductEntity.builder()
                .pin("0000")
                .cvv(generateCVV())
                .pan(generateCard())
                .inAccess(true)
                .outAccess(true)
                .isBlocked(false)
                .rbs(generateRbs())
                .clientEntity(clientEntity)
                .build();

        newProduct = productRepository.save(newProduct);

        newProduct.account = accountService.createAccount(newProduct);

        productRepository.save(newProduct);
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

    private String generateCVV(){
        var random = new Random(102);
        var sb = new StringBuilder();

        IntStream.rangeClosed(1, 3)
                .mapToObj(i -> random.nextInt())
                .forEach(sb::append);

        return sb.toString();
    }

    private String generateCard(){
        var i = 0;

        while(true){
            var generatedCardNumber = generateCardNumber(i);
            var cardOpt = productRepository.findByPan(generatedCardNumber);

            if(cardOpt.isEmpty()){
                return generatedCardNumber;
            }

            i++;
        }

    }

    private String generateCardNumber(Integer seed){
        var random = new Random(seed);
        var sb = new StringBuilder();

        IntStream.rangeClosed(1,16)
                .mapToObj(i -> random.nextInt())
                .forEach(sb::append);

        return sb.toString();
    }

    private String generateRbs(){
        var i = 0;

        while (true){
            var generatedRbs = generateCardNumber(i);

            var rbsOpt = productRepository.findByRbs(generatedRbs);
            if(rbsOpt.isEmpty()){
                return "KZ" + generatedRbs;
            }

            i++;
        }

    }
}

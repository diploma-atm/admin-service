package kz.diploma.admin.service.service.card.impl.save;

import kz.diploma.admin.service.model.response.ProductResponse;
import kz.diploma.admin.service.service.card.AccountService;
import kz.diploma.library.shared.model.entity.ClientEntity;
import kz.diploma.library.shared.model.entity.ProductEntity;
import kz.diploma.library.shared.model.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductSaveService {
    private final ProductRepository productRepository;
    private final AccountService accountService;

    public ProductResponse addClientProduct(ClientEntity clientEntity){
        var newProduct = ProductEntity.builder()
                .createdAt(LocalDateTime.now())
                .pin("0000")
                .expiredDate(LocalDate.now().plusYears(3))
                .inAccess(true)
                .outAccess(true)
                .isBlocked(false)
                .rbs(generateRbs())
                .clientEntity(clientEntity)
                .build();

        generateCard(newProduct);

        newProduct = productRepository.save(newProduct);
        newProduct.account = accountService.createAccount(newProduct);

        var ent = productRepository.save(newProduct);

        return new ProductResponse(ent);
    }


    private String generateCVV(Integer seed){
        var random = new Random(seed);
        var sb = new StringBuilder();

        IntStream.rangeClosed(1, 3)
                .mapToObj(i -> random.nextInt(10))
                .forEach(sb::append);

        return sb.toString();
    }

    private void generateCard(ProductEntity newProduct){
        var i = 0;

        while(true){
            var generatedCardNumber = generateCardNumber(i);
            var generatedCvv = generateCVV(i);
            var cardOpt = productRepository.findByPan(generatedCardNumber);

            if(cardOpt.isEmpty()){
                newProduct.cvv = generatedCvv;
                newProduct.pan = generatedCardNumber;

                return;
            }

            i++;
        }

    }

    private String generateCardNumber(Integer seed){
        var random = new Random(seed);
        var sb = new StringBuilder();

        IntStream.rangeClosed(1,16)
                .mapToObj(i -> random.nextInt(10))
                .forEach(sb::append);

        return sb.toString();
    }

    private String generateRbs(){
        var i = 0;

        while (true){
            var generatedRbs = generateCardNumber(i);

            var rbsOpt = productRepository.findByRbs("KZ" + generatedRbs);
            if(rbsOpt.isEmpty()){
                return "KZ" + generatedRbs;
            }

            i++;
        }

    }
}

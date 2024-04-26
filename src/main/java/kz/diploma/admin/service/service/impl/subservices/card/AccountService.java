package kz.diploma.admin.service.service.impl.subservices.card;

import kz.diploma.library.shared.model.entity.AccountEntity;
import kz.diploma.library.shared.model.entity.ProductEntity;

public interface AccountService {
    AccountEntity createAccount(ProductEntity product);
    void deleteAccount(Integer accountId);
}

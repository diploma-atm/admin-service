package kz.diploma.admin.service.service;

import jakarta.persistence.EntityExistsException;
import kz.diploma.library.shared.model.repository.AdminRepository;
import kz.diploma.library.shared.model.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class BaseUserService {
    protected final AdminRepository adminRepository;
    protected final ClientRepository clientRepository;

    protected void checkUserForFreePhoneNumber(String phoneNumber){
        log.info("check user for free phone number " + phoneNumber);
        var adminOpt = adminRepository.findByPhoneNumber(phoneNumber);
        var clientOpt = clientRepository.findClientByPhoneNumber(phoneNumber);

        if(adminOpt.isPresent() || clientOpt.isPresent()){
            var message = String.format("User with %s phone number already exists", phoneNumber);
            throw new EntityExistsException(message);
        }
    }
}

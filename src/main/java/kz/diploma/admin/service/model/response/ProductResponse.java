package kz.diploma.admin.service.model.response;

import kz.diploma.library.shared.model.entity.ProductEntity;

import java.time.LocalDate;

public class ProductResponse {
    public Integer id;
    public String pan;
    public String pin;
    public String cvv;
    public LocalDate expiredDate;

    public ProductResponse(ProductEntity entity){
        this.id = entity.id;
        this.pan = entity.pan;
        this.pin = entity.pin;
        this.cvv = entity.cvv;
        this.expiredDate = entity.expiredDate;
    }
}

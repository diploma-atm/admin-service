package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.model.response.ProductResponse;
import kz.diploma.admin.service.service.card.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/product/save")
    public ResponseEntity<ProductResponse> addProduct(@RequestParam(name = "clientId") Integer clientId){
        var resp = productService.addClientProduct(clientId);

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam(name = "product_id") Integer productId){
        productService.deleteClientProduct(productId);

        return ResponseEntity.ok("Delete product finished successfully");
    }

    @PutMapping("/product/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam(name = "client_id") Integer clientId){
        productService.updateClientProduct(productDTO, clientId);

        return ResponseEntity.ok("Update product finished successfully");
    }

    @GetMapping("/product/ban")
    public ResponseEntity<String> banProduct(@RequestParam(name = "productId") Integer productId){
        productService.blockProduct(productId);

        return ResponseEntity.ok("Add product to black list was successfully");
    }
}

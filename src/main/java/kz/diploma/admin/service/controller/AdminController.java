package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MainService mainService;

    @PostMapping("/client/save")
    public ResponseEntity<String> saveClient(@RequestBody ClientDTO clientDTO){
        var id = mainService.addClient(clientDTO);
        var resp = String.format("Client saved with id %d", id);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/client/delete")
    public ResponseEntity<String> deleteClient(@RequestParam(name = "client_id") Integer clientId){
        mainService.deleteClient(clientId);

        return ResponseEntity.ok("Delete client finished successfully");
    }

    @PutMapping("/client/update")
    public ResponseEntity<String> updateClient(@RequestBody ClientDTO clientDTO){
        mainService.updateClient(clientDTO);

        return ResponseEntity.ok("Update client finished successfully");
    }

    @GetMapping("/client/ban")
    public ResponseEntity<String> addClient2BlackList(@RequestParam(name = "clientId") Integer clientId){
        mainService.addClient2BlackList(clientId);

        return ResponseEntity.ok("Add client to black list was successfully");
    }


    //PRODUCT
    @PostMapping("/product/save")
    public ResponseEntity<String> addProduct(@RequestParam(name = "clientId") Integer clientId){
        mainService.addClientProduct(clientId);

        return ResponseEntity.ok("Add product was successfully, default pin 0000");
    }

    @DeleteMapping("/product/delete")
    public ResponseEntity<String> deleteProduct(@RequestParam(name = "product_id") Integer productId){
        mainService.deleteClientProduct(productId);

        return ResponseEntity.ok("Delete product finished successfully");
    }

    @PutMapping("/product/update")
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam(name = "client_id") Integer clientId){
        mainService.updateClientProduct(productDTO, clientId);

        return ResponseEntity.ok("Update product finished successfully");
    }

    @GetMapping("/product/ban")
    public ResponseEntity<String> banProduct(@RequestParam(name = "productId") Integer productId){
        mainService.blockProduct(productId);

        return ResponseEntity.ok("Add product to black list was successfully");
    }


    //ADMIN
    @PostMapping("/admin/save")
    public ResponseEntity<String> saveAdmin(@RequestBody AdminDTO adminDTO){
        var id = mainService.addAdmin(adminDTO);
        var resp = String.format("Admin saved with id %d", id);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam(name = "admin_id") Integer adminId){
        mainService.deleteAdmin(adminId);

        return ResponseEntity.ok("Delete admin finished successfully");
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminDTO adminDTO, @RequestParam(name = "admin_id") Integer adminId){
        mainService.updateAdmin(adminDTO, adminId);

        return ResponseEntity.ok("Update admin finished successfully");
    }
}

package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.model.dto.ProductDTO;
import kz.diploma.admin.service.service.MainService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final MainService mainService;

    @PostMapping("/client/save")
    public ResponseEntity<String> saveClient(@RequestBody ClientDTO clientDTO){
        mainService.addClient(clientDTO);

        return ResponseEntity.ok("Save client finished successfully");
    }

    @DeleteMapping("/client/delete")
    public ResponseEntity<String> deleteClient(@RequestParam(name = "client_id") Integer clientId){
        mainService.deleteClient(clientId);

        return ResponseEntity.ok("Delete client finished successfully");
    }

    @PutMapping("/client/update")
    public ResponseEntity<String> updateClient(@RequestBody ClientDTO clientDTO, @RequestParam(name = "client_id") Integer clientId){
        mainService.updateClient(clientDTO, clientId);

        return ResponseEntity.ok("Update client finished successfully");
    }

    @GetMapping("/client/pan")
    public ResponseEntity<ClientDTO> getClient(String pan){
        var entity = mainService.getClientByPan(pan);
        var clientDTO = new ClientDTO(entity);

        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("/client/phone-number")
    public ResponseEntity<ClientDTO> getClientByPhoneNumber(String phoneNumber){
        Logger log = LoggerFactory.getLogger(AdminController.class);
        log.info("Phone number: {}", phoneNumber);
        var entity = mainService.getClientByPhoneNumber(phoneNumber);
        var clientDTO = new ClientDTO(entity);

        return ResponseEntity.ok(clientDTO);
    }

    @GetMapping("/client/fio")
    public ResponseEntity<List<ClientDTO>> getClientByPhoneNumber(String surname, String name, String lastname){
        var entities = mainService.getClientByFio(surname, name, lastname);
        var response = entities.stream().map(ClientDTO::new).toList();

        return ResponseEntity.ok(response);
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
    public ResponseEntity<String> updateProduct(@RequestBody ProductDTO productDTO, @RequestParam(name = "client_id") Integer productId){
        mainService.updateClientProduct(productDTO, productId);

        return ResponseEntity.ok("Update product finished successfully");
    }

    @GetMapping("/product/ban")
    public ResponseEntity<String> banProduct(@RequestParam(name = "productId") Integer productId){
        mainService.blockProduct(productId);

        return ResponseEntity.ok("Add product to black list was successfully");
    }


    //ADMIN
    @PostMapping("/admin/save")
    public ResponseEntity<String> saveClient(@RequestBody AdminDTO adminDTO){
        mainService.addAdmin(adminDTO);

        return ResponseEntity.ok("Save admin finished successfully");
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam(name = "admin_id") Integer adminId){
        mainService.deleteClient(adminId);

        return ResponseEntity.ok("Delete admin finished successfully");
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String> updateClient(@RequestBody AdminDTO adminDTO, @RequestParam(name = "admin_id") Integer adminId){
        mainService.updateAdmin(adminDTO, adminId);

        return ResponseEntity.ok("Update admin finished successfully");
    }
}

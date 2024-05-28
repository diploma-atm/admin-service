package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.ClientDTO;
import kz.diploma.admin.service.service.client.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client/save")
    public ResponseEntity<String> saveClient(@RequestBody ClientDTO clientDTO){
        var id = clientService.addClient(clientDTO);
        var resp = String.format("Client saved with id %d", id);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/client/delete")
    public ResponseEntity<String> deleteClient(@RequestParam(name = "client_id") Integer clientId){
        clientService.deleteClient(clientId);

        return ResponseEntity.ok("Delete client finished successfully");
    }

    @PutMapping("/client/update")
    public ResponseEntity<String> updateClient(@RequestBody ClientDTO clientDTO){
        clientService.updateClient(clientDTO);

        return ResponseEntity.ok("Update client finished successfully");
    }

    @GetMapping("/client/ban")
    public ResponseEntity<String> addClient2BlackList(@RequestParam(name = "clientId") Integer clientId){
        clientService.addClient2BlackList(clientId);

        return ResponseEntity.ok("Add client to black list was successfully");
    }


}

package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.request.ClientRequest;
import kz.diploma.admin.service.service.client.ClientService;
import kz.diploma.shared.library.security.annotation.RolesAllowed;
import kz.diploma.shared.library.security.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@RolesAllowed(value = Roles.ADMIN)
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/client/save")
    public ResponseEntity<Integer> saveClient(@RequestBody ClientRequest clientRequest){
        var id = clientService.addClient(clientRequest);

        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/client/delete")
    public ResponseEntity<String> deleteClient(@RequestParam(name = "client_id") Integer clientId){
        clientService.deleteClient(clientId);

        return ResponseEntity.ok("Delete client finished successfully");
    }

    @PutMapping("/client/update")
    public ResponseEntity<String> updateClient(@RequestBody ClientRequest clientRequest){
        clientService.updateClient(clientRequest);

        return ResponseEntity.ok("Update client finished successfully");
    }

    @GetMapping("/client/ban")
    public ResponseEntity<String> addClient2BlackList(@RequestParam(name = "clientId") Integer clientId){
        clientService.addClient2BlackList(clientId);

        return ResponseEntity.ok("Add client to black list was successfully");
    }


}

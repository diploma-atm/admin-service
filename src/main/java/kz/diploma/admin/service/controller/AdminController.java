package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.model.response.MessageResponse;
import kz.diploma.admin.service.service.admin.AdminService;
import kz.diploma.shared.library.security.annotation.RolesAllowed;
import kz.diploma.shared.library.security.model.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
@RolesAllowed(value = Roles.ADMIN)
public class AdminController {
    private final AdminService adminService;

    //ADMIN
    @PostMapping("/admin/save")
    public ResponseEntity<MessageResponse> saveAdmin(@RequestBody AdminDTO adminDTO){
        var id = adminService.addAdmin(adminDTO).toString();
        return ResponseEntity.ok(new MessageResponse(id));
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<MessageResponse> deleteAdmin(@RequestParam(name = "admin_id") Integer adminId){
        adminService.deleteAdmin(adminId);

        return ResponseEntity.ok(new MessageResponse("Delete admin finished successfully"));
    }

    @PutMapping("/admin/update")
    public ResponseEntity<MessageResponse> updateAdmin(@RequestBody AdminDTO adminDTO){
        adminService.updateAdmin(adminDTO);

        return ResponseEntity.ok(new MessageResponse("Update admin finished successfully"));
    }
}

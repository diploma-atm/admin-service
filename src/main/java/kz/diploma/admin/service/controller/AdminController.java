package kz.diploma.admin.service.controller;

import kz.diploma.admin.service.model.dto.AdminDTO;
import kz.diploma.admin.service.service.admin.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    //ADMIN
    @PostMapping("/admin/save")
    public ResponseEntity<String> saveAdmin(@RequestBody AdminDTO adminDTO){
        var id = adminService.addAdmin(adminDTO);
        var resp = String.format("Admin saved with id %d", id);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/admin/delete")
    public ResponseEntity<String> deleteAdmin(@RequestParam(name = "admin_id") Integer adminId){
        adminService.deleteAdmin(adminId);

        return ResponseEntity.ok("Delete admin finished successfully");
    }

    @PutMapping("/admin/update")
    public ResponseEntity<String> updateAdmin(@RequestBody AdminDTO adminDTO, @RequestParam(name = "admin_id") Integer adminId){
        adminService.updateAdmin(adminDTO, adminId);

        return ResponseEntity.ok("Update admin finished successfully");
    }
}

package poly.quanlyquanao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import poly.quanlyquanao.model.Role;
import poly.quanlyquanao.service.Impl.IRoleService;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/role")
public class RoleRestController {
    @Autowired
    private IRoleService roleService;
    @GetMapping("/list")
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}

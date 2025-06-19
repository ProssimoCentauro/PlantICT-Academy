package it.academy.firstStep.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import it.academy.firstStep.dto.RoleDto;
import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.exceptions.RoleNotFoundException;
import it.academy.firstStep.requestObjects.RoleRequestObject;
import it.academy.firstStep.service.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role successfully created")
    })
    public RoleEntity createRole(@RequestBody RoleRequestObject roleData) {
        return roleService.createRole(roleData);
    }

    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role successfully updated"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public RoleEntity updateRole(@PathVariable Integer id, @RequestBody RoleDto roleDto) {
        return roleService.updateRole(id, roleDto);
    }

    @GetMapping("/getallroles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of roles retrieved successfully")
    })
    public List<RoleEntity> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/getrolebyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public RoleEntity getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id);
    }

    @DeleteMapping("/deleterolebyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Role successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Role not found")
    })
    public void deleteRoleById(@PathVariable Integer id) {
        roleService.deleteRoleById(id);
    }
}

package it.academy.firstStep.controller;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.dto.UserRoleDto;
import it.academy.firstStep.entities.UserRoleEntity;
import it.academy.firstStep.exceptions.RoleNotFoundException;
import it.academy.firstStep.exceptions.UserNotFoundException;
import it.academy.firstStep.exceptions.UserRoleNotFoundException;
import it.academy.firstStep.rolesEnum.Roles;
import it.academy.firstStep.service.UserRoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userrole")
public class UserRoleController {

    private final UserRoleService userRoleService;

    @Autowired
    public UserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/add/{userid}/{role}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserRole successfully created"),
            @ApiResponse(responseCode = "404", description = "User or Role not found")
    })
    public UserRoleEntity createUserRole(@PathVariable int userid, @PathVariable Roles role) {
        return userRoleService.createUserRole(userid, role);
        }
    /*
    @PostMapping("/add/{userid}/{roleid}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserRole successfully created"),
            @ApiResponse(responseCode = "404", description = "User or Role not found")
    })
    public UserRoleEntity createUserRole( @RequestBody UserRoleDto userRoleDto) {
        return userRoleService.createUserRole(userRoleDto);
    }*/

    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserRole successfully updated"),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    public UserRoleEntity updateUserRole(@PathVariable Integer id, @RequestBody UserRoleDto userRoleDto) {
        return userRoleService.updateUserRole(id, userRoleDto);
    }

    @GetMapping("/getalluserroles")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of user roles retrieved successfully")
    })
    public List<UserRoleEntity> getAllUserRoles() {
        return userRoleService.getAllUserRoles();
    }

    @GetMapping("/getuserrolebyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserRole retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    public UserRoleEntity getUserRoleById(@PathVariable Integer id) {
        return userRoleService.getUserRoleById(id);
    }

    @DeleteMapping("/deleteuserrolebyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserRole successfully deleted"),
            @ApiResponse(responseCode = "404", description = "UserRole not found")
    })
    public void deleteUserRoleById(@PathVariable Integer id) {
        userRoleService.deleteUserRoleById(id);
    }
}

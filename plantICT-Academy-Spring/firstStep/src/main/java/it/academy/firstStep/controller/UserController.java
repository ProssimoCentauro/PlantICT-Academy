package it.academy.firstStep.controller;

import it.academy.firstStep.dto.UserDto;
import it.academy.firstStep.entities.UserEntity;
import it.academy.firstStep.requestObjects.UserRequestObject;
import it.academy.firstStep.rolesEnum.Roles;
import it.academy.firstStep.service.UserService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
/*
    @PostMapping("/add")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    public UserEntity createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }*/

    @PostMapping("/add/{role}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully created"),
            @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    public UserEntity createUser(@RequestBody UserRequestObject user, @PathVariable Roles role) {
        return userService.createUser(user, role);
    }


    @PutMapping("/update/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully updated"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "Username already exists")
    })
    public UserEntity updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }

    @GetMapping("/getallusers")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    })
    public List<UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/getuserbyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public UserEntity getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/deleteuserbyid/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User successfully deleted"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public void deleteUserById(@PathVariable Integer id) {
        userService.deleteUserById(id);
    }
}

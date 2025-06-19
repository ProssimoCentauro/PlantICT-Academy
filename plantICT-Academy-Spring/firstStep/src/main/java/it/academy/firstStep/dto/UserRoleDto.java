package it.academy.firstStep.dto;

import it.academy.firstStep.entities.RoleEntity;
import it.academy.firstStep.entities.UserEntity;

import java.time.LocalDateTime;

public class UserRoleDto extends BaseDto {

    private int id;
 //   private int userId;
 //   private int roleId;
    private UserEntity user;
    private RoleEntity role;
 /*   private LocalDateTime insertDate;
    private LocalDateTime lastUpdateDate;
    private String operator = "system";*/

    public UserRoleDto() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
/*
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
*/

    public UserEntity getUser() {
        return user;
    }
    public void setUser(UserEntity user) {
        this.user = user;
    }
    public RoleEntity getRole() {
        return role;
    }
    public void setRole(RoleEntity role) {
        this.role = role;
    }

 /*   public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }*/
}

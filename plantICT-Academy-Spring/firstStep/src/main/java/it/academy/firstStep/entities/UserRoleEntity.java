package it.academy.firstStep.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_role", uniqueConstraints = {
        @UniqueConstraint(name = "user_role_uk", columnNames = {"id_user", "id_role"})
})
public class UserRoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_role")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_role", nullable = false)
    private RoleEntity role;


/*
    @Column(name = "id_user", nullable = false)
    private int UserId;

    @Column(name = "id_role", nullable = false)
    private int RoleId;
*/
/*    @Column(name = "insert_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime insertDate;

    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false, length = 255)
    private String operator = "system";*/

    public UserRoleEntity() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


/*    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }
*/
/*    public LocalDateTime getInsertDate() {
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

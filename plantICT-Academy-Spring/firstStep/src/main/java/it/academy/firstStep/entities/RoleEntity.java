package it.academy.firstStep.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "name_uk", columnNames = "name")
})
public class    RoleEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_role")
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 255)
    private String description;

/*    @Column(name = "insert_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime insertDate;

    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false, length = 255)
    private String operator = "system";*/

    public RoleEntity() {}

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  /*  public LocalDateTime getInsertDate() {
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


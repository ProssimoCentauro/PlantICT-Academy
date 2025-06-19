package it.academy.firstStep.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {
    @Column(name = "insert_date", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime insertDate;

    @Column(name = "last_update_date", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @Column(nullable = false, length = 255)
    private String operator = "system";

    public LocalDateTime getInsertDate() {
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
    }
}

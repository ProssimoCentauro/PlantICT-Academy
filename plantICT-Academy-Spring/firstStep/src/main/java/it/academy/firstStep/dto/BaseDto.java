package it.academy.firstStep.dto;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseDto {

    private LocalDateTime insertDate;
    private LocalDateTime lastUpdateDate;
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

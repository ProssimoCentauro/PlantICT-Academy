package com.academy.ggTournaments.requestObject;

import com.academy.ggTournaments.entity.BaseEntity;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PlayerRequestObject {
    private String name;
    private String surname;
    private String sponsor;
    private LocalDate birthdate;
    private Integer rankingAtp;
}


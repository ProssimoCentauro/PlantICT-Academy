package com.academy.ggTournaments.requestObject;

import com.academy.ggTournaments.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TournamentRequestObject {
    private String name;
    private String location;
}

package com.anels.premier_league.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "player_data")
public class Player {

    @Id
    @Column(name = "player_name", unique = true)
    private String playerName;

    private String nation;

    private String position;

    private Integer age;

    private Integer matchesPlayed;

    private Integer starts;

    private Float minutesPlayed;

    private Float goals;

    private Float assists;

    private Float penaltiesScored;

    private Float yellowCards;

    private Float redCards;

    private Float expectedGoals;

    private Float expectedAssists;

    private String teamName;


}

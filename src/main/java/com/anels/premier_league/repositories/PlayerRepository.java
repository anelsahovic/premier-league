package com.anels.premier_league.repositories;

import com.anels.premier_league.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    void deleteByPlayerName(String playerName);

    Optional<Player> findByPlayerName(String playerName);

}

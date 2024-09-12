package com.anels.premier_league.services;

import com.anels.premier_league.domain.entities.Player;
import com.anels.premier_league.repositories.PlayerRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(final PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public List<Player> getPlayersFromTeam(String teamName) {
        return playerRepository.findAll().stream().filter(player -> player.getTeamName().equals(teamName))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByName(String searchedText) {
        return playerRepository.findAll().stream().filter(player -> player.getPlayerName().toLowerCase().contains(searchedText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByPosition(String searchedText) {
        return playerRepository.findAll().stream().filter(player -> player.getPosition().toLowerCase().contains(searchedText.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByNation(String nation) {
        return playerRepository.findAll().stream().filter(player -> player.getNation().toLowerCase().equals(nation.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Player> getPlayersByTeamAndPosition(String teamName, String position) {
        return playerRepository.findAll().stream().filter(player -> player.getTeamName().toLowerCase().equals(teamName) &&
                player.getPosition().toLowerCase().equals(position)).collect(Collectors.toList());
    }

    public Player addPlayer(Player playerToAdd) {
        playerRepository.save(playerToAdd);
        return playerToAdd;
    }

    public Player updatePlayer(Player updatedPlayer) {
        Optional<Player> existingPlayer = playerRepository.findByPlayerName(updatedPlayer.getPlayerName());
        if (existingPlayer.isPresent()) {
            Player playerToUpdate = existingPlayer.get();
            playerToUpdate.builder()
                    .playerName(updatedPlayer.getPlayerName())
                    .teamName(updatedPlayer.getTeamName())
                    .position(updatedPlayer.getPosition())
                    .nation(updatedPlayer.getNation())
                    .build();

            playerRepository.save(playerToUpdate);
            return playerToUpdate;
        }
        return null;
    }

    @Transactional
    public void deletePlayer(String playerName) {
        playerRepository.deleteByPlayerName(playerName);
    }
}

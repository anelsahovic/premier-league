package com.anels.premier_league.controllers;

import com.anels.premier_league.player.Player;
import com.anels.premier_league.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(final PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<Player> getPlayers(
            @RequestParam(required = false) String team,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String position,
            @RequestParam(required = false) String nation
    ) {
        if (team != null && position != null) {
            return playerService.getPlayersByTeamAndPosition(team, position);
        } else if (name != null) {
            return playerService.getPlayersByName(name);
        } else if (nation != null) {
            return playerService.getPlayersByNation(nation);
        } else if (team != null) {
            return playerService.getPlayersFromTeam(team);
        } else if (position != null) {
            return playerService.getPlayersByPosition(position);
        } else {
            return playerService.getAllPlayers();
        }
    }

    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.addPlayer(player);
        return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Player> updatePlayer(@RequestBody Player player) {
        Player resultPlayer = playerService.updatePlayer(player);
        if (resultPlayer != null) {
            return new ResponseEntity<>(resultPlayer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{playerName}")
    public ResponseEntity<String> deletePlayer(@PathVariable("playerName") String playerName) {
          playerService.deletePlayer(playerName);
          return new ResponseEntity<>("Player Deleted Succesfuly", HttpStatus.OK);
    }


}

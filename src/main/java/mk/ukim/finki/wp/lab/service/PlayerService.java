package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> listPlayers();
   Optional <Player> findByPlayerId(String playerId);

}

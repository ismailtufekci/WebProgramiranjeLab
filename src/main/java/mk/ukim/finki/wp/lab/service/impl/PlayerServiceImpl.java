package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.service.PlayerService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    public PlayerServiceImpl(PlayerRepository playerRepository){
        this.playerRepository = playerRepository;
    }

    @Override
    public List<Player> listPlayers() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findByPlayerId(String playerId) {
        return playerRepository.findByPlayerId(playerId);
    }



}

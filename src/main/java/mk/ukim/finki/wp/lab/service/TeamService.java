package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;

import java.util.List;
import java.util.Optional;

public interface TeamService  {
    List<Team> listTeams();
    Optional<Team> findById(Long id);
    Team addPlayerToTeam(Long teamId, String playerId);
}

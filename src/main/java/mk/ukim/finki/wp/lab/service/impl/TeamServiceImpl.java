package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import mk.ukim.finki.wp.lab.repository.PlayerRepository;
import mk.ukim.finki.wp.lab.repository.TeamRepository;
import mk.ukim.finki.wp.lab.service.TeamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;
    public TeamServiceImpl (TeamRepository teamRepository,PlayerRepository playerRepository){
        this.teamRepository=teamRepository;
        this.playerRepository=playerRepository;
    }

    @Override
    public List<Team> listTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Optional<Team> findById(Long id) {
        return Optional.of(teamRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Team not found")));
    }

    @Override
    public Team addPlayerToTeam(Long teamId, String playerId) {

        // 1. Find team
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        // 2. Find player
        Player player = playerRepository.findByPlayerId(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found"));

        // 3. Check if player already belongs to a team
        teamRepository.findAll().stream()
                .filter(t -> t.getPlayers().stream()
                        .anyMatch(p -> p.getPlayerId().equals(playerId)))
                .findFirst()
                .ifPresent(t -> {
                    throw new IllegalArgumentException(
                            "Player already plays for " + t.getName()
                    );
                });

        // 4. Check if jersey number is already taken
        boolean jerseyTaken = team.getPlayers().stream()
                .anyMatch(p -> p.getJerseyNumber() == player.getJerseyNumber());

        if (jerseyTaken) {
            throw new IllegalArgumentException(
                    "Jersey number " + player.getJerseyNumber()
                            + " is already taken in this team"
            );
        }

        // 5. Add player to team
        team.getPlayers().add(player);

        // 6. Save and return team
        return teamRepository.save(team);
    }


}


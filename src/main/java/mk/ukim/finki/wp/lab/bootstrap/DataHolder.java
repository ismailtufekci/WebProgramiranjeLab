package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Player;
import mk.ukim.finki.wp.lab.model.Team;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Team> teams = new ArrayList<>();
    public static List<Player> players = new ArrayList<>();


    public DataHolder(){

    }
    @PostConstruct
    public void init(){
        teams.add(new Team(1L,"Manchester","Mancheser"));
        teams.add(new Team(2L,"Fullham","Fullham"));
        teams.add(new Team(3L,"Napredok","Kichevo"));
        teams.add(new Team(4L,"RealMadrid","Madrid"));
        teams.add(new Team(5L,"Chelsea","London"));
        players.add(new Player("1","Zlatan","Ibrahimovic", "Forward",6));
        players.add(new Player("2", "Lionel", "Messi", "Forward", 10));
        players.add(new Player("3", "Cristiano", "Ronaldo", "Forward", 7));
        players.add(new Player("4", "Neymar", "Junior", "Forward", 10));
        players.add(new Player("5", "Kevin", "De Bruyne", "Midfielder", 17));
        players.add(new Player("6", "Luka", "Modric", "Midfielder", 10));
        players.add(new Player("7", "Sergio", "Ramos", "Defender", 4));
        players.add(new Player("8", "Virgil", "van Dijk", "Defender", 4));
        players.add(new Player("9", "Kylian", "Mbappe", "Forward", 9));
        players.add(new Player("10", "Manuel", "Neuer", "Goalkeeper", 1));

    }
}

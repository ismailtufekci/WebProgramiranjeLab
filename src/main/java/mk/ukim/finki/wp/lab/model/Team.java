package mk.ukim.finki.wp.lab.model;
import lombok.*;
import lombok.Lombok;

import java.util.List;


@Data
@AllArgsConstructor
public class Team {

  private final Long id;
   private final String name;
    private final String city;
    private  List<Player> players;
    public Team (Long id, String name, String city){
        this.id=id;
        this.name=name;
        this.city=city;
    }



}


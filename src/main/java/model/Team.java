package model;

import lombok.Builder;
import lombok.Data;
import java.util.Queue;

@Data
@Builder
public class Team {
    String teamName;
    Queue<Player> players;
}

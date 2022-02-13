package model;

import lombok.Builder;
import lombok.Data;
import java.util.Queue;

@Data
@Builder
public class Team {
    private final String teamName;
    private final Queue<Player> players;
}

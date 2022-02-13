package model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Match {
    private final int totalNoOfOversInAnInnings;
    private final int noOfInnings;
    private int currentInnings;
    private boolean isMatchFinished;
    private final int noOfPlayersInEachTeam;

    final List<Innings> inningsList;
    final List<Team> teamsPlaying;

    public void addInnings(Innings inning){
        inningsList.add(inning);
    }
}

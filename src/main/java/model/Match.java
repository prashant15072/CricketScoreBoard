package model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Match {
    int totalNoOfOversInAnInnings;
    int noOfInnings;
    int currentInnings;
    boolean isMatchFinished;
    int noOfPlayersInEachTeam;

    List<Innings> inningsList;
    List<Team> teamsPlaying;

    public void addInnings(Innings inning){
        if (inningsList==null){
            inningsList = new ArrayList<>();
        }

        inningsList.add(inning);
    }
}

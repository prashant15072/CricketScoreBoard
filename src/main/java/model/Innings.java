package model;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
@Builder
public class Innings {
    final int totalNoOfOvers;
    int oversPlayed=0;
    int runsScored=0;
    int wicketsDown = 0;
    int totalExtras = 0;

    final Team BattingTeam;
    final Queue<Player> batsmenWaitingInPavilion;
    final LinkedList<Player> batsmenBatting;

    List<Over> overs;

    public void addExtras(int val){
        totalExtras+=val;
    }

}

package model;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
@Builder
public class Innings {
    private final int totalNoOfOvers;
    private int oversPlayed=0;
    private int runsScored=0;
    private int wicketsDown = 0;
    private int totalExtras = 0;

    private final Team BattingTeam;
    private final Queue<Player> batsmenWaitingInPavilion;
    private final LinkedList<Player> batsmenBatting;

    private final List<Over> overs;

    public void addExtras(int val){
        totalExtras+=val;
    }

}

package model;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Data
public class Innings {
    int totalNoOfOvers;
    int oversPlayed=0;
    int runsScored=0;
    int wicketsDown = 0;
    int totalExtras = 0;

    Team BattingTeam;
    Queue<Player> batsmenWaitingInPavilion;
    LinkedList<Player> batsmenBatting;

    List<Over> overs;

    public void addExtras(int val){
        totalExtras+=val;
    }

}

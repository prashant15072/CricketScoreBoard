package service;

import model.Innings;
import model.Player;

import java.util.LinkedList;

public class ConsoleOutputService implements OutputService {

    @Override
    public void printOverNumber(int overNumber) {
        System.out.println("Over "+overNumber);
    }

    @Override
    public void printScoreCard(Innings currentInnings, LinkedList<Player> batsmenBatting) {
        System.out.println("ScoreCard for "+currentInnings.getBattingTeam().getTeamName());
        System.out.format("%10s %10s %10s %10s %20s %20s\n","PlayerName","Score","4s","6s","BallsPlayed","StrikeRate");

        currentInnings.getBattingTeam().getPlayers().forEach(player -> {
            String playerName = player.getName();
            boolean isbatsmenBatting = batsmenBatting.stream().anyMatch(p -> p.getName().equals(player.getName()));

            if(isbatsmenBatting){
                playerName = playerName+"*";
            }

            System.out.format("%10s %10s %10s %10s %20s %20.2f\n",playerName
                    ,player.getRunScored(),player.getNoOfFours()
                    ,player.getNoOfSixes(),player.getNoOfBallsPlayed(),
                    player.getStrikRate());
        });

        System.out.println("Total :"+currentInnings.getRunsScored()+"/"+ currentInnings.getWicketsDown());
        System.out.println("Overs :" + currentInnings.getOversPlayed());
        System.out.println("Extras :"+currentInnings.getTotalExtras());
    }
}

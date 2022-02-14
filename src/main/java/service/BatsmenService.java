package service;

import Utils.RunType;
import exception.InvalidActionException;
import model.*;

import java.util.LinkedList;
import java.util.Queue;

public class BatsmenService{
    private final Match matchInfo;
    private Queue<Player> batsmenWaitingInPavilion;
    private Innings currentInnings;

    BatsmenService(Match match){
        this.matchInfo=match;
    }

    private void updateBatsmenQueue(){
        currentInnings = matchInfo.getInningsList().get(matchInfo.getInningsList().size()-1);
        batsmenWaitingInPavilion = currentInnings.getBatsmenWaitingInPavilion();
    }

    void addBattingOrder(Team team, Queue<Player> requiredBattingOrder){
        updateBatsmenQueue();

        if (requiredBattingOrder.size()<matchInfo.getNoOfPlayersInEachTeam()) {
            throw new InvalidActionException("Players in the team are less than expected!");
        }

        batsmenWaitingInPavilion = currentInnings.getBatsmenWaitingInPavilion();
        batsmenWaitingInPavilion.addAll(requiredBattingOrder);
        team.getPlayers().addAll(requiredBattingOrder);
    }

    void updateBatsmenScore(Ball ball){
        Player player = currentInnings.getOnStrikeBatsmen();
        if (ball.isWicket())
        {
            currentInnings.setOnStrikeBatsmen(null);
            if (!batsmenWaitingInPavilion.isEmpty())currentInnings.setOnStrikeBatsmen(batsmenWaitingInPavilion.poll());
            player.setNoOfBallsPlayed(player.getNoOfBallsPlayed()+1);

        }else{
            player.addRuns(ball.getRunsScored());
            if (ball.isCounted())player.setNoOfBallsPlayed(player.getNoOfBallsPlayed()+1);

            if (ball.getRunsScored()==RunType.FOUR.getRuns()){
                player.setNoOfFours(player.getNoOfFours()+1);
            }else if (ball.getRunsScored()==RunType.SIX.getRuns()){
                player.setNoOfSixes(player.getNoOfSixes()+1);
            }

            if (ball.getRunsScored()%2!=0){
                rotateStrike();
            }
        }
    }

    void rotateStrike(){
        if (!isBattingPairAvailable()){
            throw new InvalidActionException("Not enough batsmen to rotate the strike");
        }
        Player temp = currentInnings.getOnStrikeBatsmen();
        currentInnings.setOnStrikeBatsmen(currentInnings.getOffStrikeBatsmen());
        currentInnings.setOffStrikeBatsmen(temp);
    }

    boolean isBattingPairAvailable(){
        if (currentInnings.getOnStrikeBatsmen()==null || currentInnings.getOffStrikeBatsmen()==null){
            return false;
        }
        return true;
    }

    void addOpeningBatsmenPair() {
        currentInnings.setOnStrikeBatsmen(batsmenWaitingInPavilion.poll());
        currentInnings.setOffStrikeBatsmen(batsmenWaitingInPavilion.poll());
    }
}
package service;

import Utils.RunType;
import exception.InvalidActionException;
import model.*;

import java.util.LinkedList;
import java.util.Queue;

public class BatsmenService{
    Match matchInfo;
    Queue<Player> batsmenWaitingInPavilion;
    LinkedList<Player> batsmenBatting;
    Innings currentInnings;

    public BatsmenService(Match match){
        this.matchInfo=match;
    }

    private void updateBatsmenQueue(){
        currentInnings = matchInfo.getInningsList().get(matchInfo.getInningsList().size()-1);
        batsmenBatting=currentInnings.getBatsmenBatting();
        batsmenWaitingInPavilion = currentInnings.getBatsmenWaitingInPavilion();
    }

    void addBattingOrder(Team team, Queue<Player> requiredBattingOrder){
        updateBatsmenQueue();

        if (requiredBattingOrder.size()<matchInfo.getNoOfPlayersInEachTeam()) {
            throw new InvalidActionException("Players in the team are less than expected!");
        }

        batsmenWaitingInPavilion = currentInnings.getBatsmenWaitingInPavilion();
        batsmenWaitingInPavilion.addAll(requiredBattingOrder);

        team.setPlayers(requiredBattingOrder);
    }

    void updateBatsmenScore(Ball ball){
        Player player = batsmenBatting.peek();
        if (ball.isWicket())
        {
            batsmenBatting.poll();
            if (!batsmenWaitingInPavilion.isEmpty())batsmenBatting.addFirst(batsmenWaitingInPavilion.poll());
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
        if (batsmenBatting.size()<2){
            throw new InvalidActionException("Not enough batsmen to rotate the strike");
        }
        batsmenBatting.addLast(batsmenBatting.poll());
    }

    void addOpeningBatsmenPair() {
        batsmenBatting.add(batsmenWaitingInPavilion.poll());
        batsmenBatting.add(batsmenWaitingInPavilion.poll());
    }
}
package service;

import Utils.Constants;
import model.Ball;
import model.Innings;
import model.Over;
import model.Player;

import java.util.ArrayList;
import java.util.List;

public class OverService {
    private OutputService outputService;
    private InputService inputService;
    private BatsmenService batsmenService;

    OverService(InputService inputService, OutputService outputService, BatsmenService batsmenService){
        this.outputService = outputService;
        this.inputService = inputService;
        this.batsmenService = batsmenService;
    }

    void startOvers(Innings currentInnings,boolean isLastInnings,int runsToWin){
        for (int i=0;i<currentInnings.getTotalNoOfOvers();i++){
            boolean isInningsCompleted = startNewOver(currentInnings,isLastInnings,runsToWin);

            outputService.printScoreCard(currentInnings,currentInnings.getOnStrikeBatsmen(),currentInnings.getOffStrikeBatsmen());

            if (isInningsCompleted)break;
            batsmenService.rotateStrike();
        }
    }

    private boolean startNewOver(Innings currentInnings,boolean isLastInnings,int runsToWin){
        int overNumber = currentInnings.getOversPlayed();
        List<Ball> ballArrayList = new ArrayList<>();
        Over over = Over.builder().number(++overNumber).balls(ballArrayList).build();

        outputService.printOverNumber(overNumber);

        int i=0;
        while(i< Constants.DEFAULT_BALLS_IN_AN_OVER){
            Ball ball = inputService.getBall();
            ballArrayList.add(ball);

            batsmenService.updateBatsmenScore(ball);

            if (ball.isWicket())
            {
                currentInnings.setWicketsDown(currentInnings.getWicketsDown()+1);
                //Team is All Out
                if (!batsmenService.isBattingPairAvailable()) {
                    completeOver(over,currentInnings);
                    return true;
                }
            }

            else if (ball.isExtras()){
                over.addExtras(ball.getExtraRuns());
                over.addToTotalRuns(ball.getRunsScored() + ball.getExtraRuns());
                currentInnings.addExtras(ball.getExtraRuns());
                i--; // Since this ball will not be counted
            }else{
                over.addRunScored(ball.getRunsScored());
                over.addToTotalRuns(ball.getRunsScored());
            }

            if (isLastInnings && currentInnings.getRunsScored()+over.getTotalRunsInAnOver()>=runsToWin){
                    completeOver(over,currentInnings);
                    return true;
            }

            i++;
        }

        completeOver(over,currentInnings);
        return false;
    }

    private void completeOver(Over over,Innings currentInnings){
        currentInnings.getOvers().add(over);
        currentInnings.setRunsScored(currentInnings.getRunsScored()+over.getTotalRunsInAnOver());
        currentInnings.setOversPlayed(over.getNumber());
    }
}

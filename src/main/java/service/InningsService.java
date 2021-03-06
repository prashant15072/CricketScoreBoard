package service;

import model.Innings;
import model.Match;
import model.Player;
import model.Team;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class InningsService {

    private final Match matchInfo;
    private BatsmenService batsmenService;
    private InputService inputService;
    private OverService overService;

    InningsService(Match match,BatsmenService batsmenService,InputService inputService,OutputService outputService){
        this.matchInfo = match;
        this.batsmenService = batsmenService;
        this.inputService = inputService;
        this.overService = new OverService(inputService,outputService,batsmenService);
    }

    void startNewInnings(boolean isLastInnings,int runsToWin,boolean isFirstTeamBatting){
        Innings innings =getInnings(matchInfo,isFirstTeamBatting);
        matchInfo.addInnings(innings);
        matchInfo.setCurrentInnings(matchInfo.getCurrentInnings()+1);

        Queue<Player> requiredBattingOrder = inputService.getBattingOrder(innings.getBattingTeam(),matchInfo.getNoOfPlayersInEachTeam());
        batsmenService.addBattingOrder(innings.getBattingTeam(),requiredBattingOrder);

        batsmenService.addOpeningBatsmenPair();
        overService.startOvers(innings,isLastInnings,runsToWin);
    }

    private Innings getInnings(Match matchInfo,boolean firstTeamBatting){

        Team team ;
        if (firstTeamBatting){
            team = matchInfo.getTeamsPlaying().get(0);
        }else{
            team = matchInfo.getTeamsPlaying().get(1);
        }

        return Innings.builder().totalNoOfOvers(matchInfo.getTotalNoOfOversInAnInnings()).BattingTeam(team)
                .batsmenWaitingInPavilion(new LinkedList<>()).overs(new ArrayList<>()).build();
    }
}

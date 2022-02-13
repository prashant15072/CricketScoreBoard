package service;

import model.Innings;
import model.Match;
import model.Player;
import model.Team;

import java.util.Queue;

public class InningsService {

    Match matchInfo;
    BatsmenService batsmenService;
    InputService inputService;
    OverService overService;

    public InningsService(Match match,BatsmenService batsmenService,InputService inputService,OutputService outputService){
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
        Innings innings = new Innings();
        innings.setTotalNoOfOvers(matchInfo.getTotalNoOfOversInAnInnings());

        Team team ;
        if (firstTeamBatting){
            team = matchInfo.getTeamsPlaying().get(0);
        }else{
            team = matchInfo.getTeamsPlaying().get(1);
        }
        innings.setBattingTeam(team);

        return innings;
    }
}

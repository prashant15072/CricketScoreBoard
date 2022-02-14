package service;

import Utils.Constants;
import exception.InvalidActionException;
import model.*;

import java.util.*;

public class MatchService {
    private final Match matchInfo;
    private boolean isFirstTeamBatting = true;
    private int runsToWin = -1;

    private InputService inputService;
    private OutputService outputService;
    private BatsmenService batsmenService;
    private InningsService inningsService;

    public MatchService(int noOfPlayers, int noOfOvers, InputService inputService, OutputService outputService){
        List<Team> teams = new ArrayList<>(Constants.NO_OF_TEAMS_PLAYING);
        teams.add(Team.builder().teamName(Constants.DEFAULT_TEAM_ONE_NAME).players(new LinkedList<>()).build());
        teams.add(Team.builder().teamName(Constants.DEFAULT_TEAM_TWO_NAME).players(new LinkedList<>()).build());
        matchInfo = Match.builder().noOfInnings(Constants.DEFAULT_NO_OF_INNINGS).noOfPlayersInEachTeam(noOfPlayers).
                totalNoOfOversInAnInnings(noOfOvers).teamsPlaying(teams).inningsList(new ArrayList<>()).build();


        this.inputService = inputService;
        this.outputService = outputService;
        this.batsmenService = new BatsmenService(matchInfo);
        this.inningsService = new InningsService(this.matchInfo,this.batsmenService,this.inputService,this.outputService);
    }

    public void startMatch(){
        if (matchInfo.isMatchFinished()){
            throw new InvalidActionException("Match is already Finished");
        }

        for (int i=0;i<matchInfo.getNoOfInnings();i++){
            if (i==matchInfo.getNoOfInnings()-1) inningsService.startNewInnings(true, getRunsToWin(), isFirstTeamBatting);
            else inningsService.startNewInnings(false, -1, isFirstTeamBatting); isFirstTeamBatting=!isFirstTeamBatting;
        }

        getResults();
    }

    private void getResults() {
        int teamOneScore = 0;
        int teamTwoScore = 0;

        List<Innings> innings = matchInfo.getInningsList();
        for (int i=0;i<matchInfo.getNoOfInnings();i=i+2){
            teamOneScore+=innings.get(i).getRunsScored();
            teamTwoScore+=innings.get(i+1).getRunsScored();
        }

        if (teamOneScore>teamTwoScore){
            System.out.println("Team 1 Won");
        }else if (teamOneScore<teamTwoScore){
            System.out.println("Team 2 Won");
        }else{
            System.out.println("Match Tied");
        }
    }

    private int getRunsToWin() {
        if (runsToWin!=-1){
            return runsToWin;
        }

        List<Innings> inningsList = matchInfo.getInningsList();
        runsToWin = 0;

        for(int i =0;i<inningsList.size()-2;i=i+2){
            runsToWin+=inningsList.get(i).getRunsScored()-inningsList.get(i+1).getRunsScored();
        }
        runsToWin+=inningsList.get(inningsList.size()-1).getRunsScored()+1;

        return runsToWin;
    }


}

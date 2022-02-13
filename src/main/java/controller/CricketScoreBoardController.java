package controller;

import Utils.Constants;
import exception.InvalidNumberOfOversException;
import exception.InvalidNumberOfPlayersException;
import service.*;

public class CricketScoreBoardController {

    public void startMatch(){
        InputService inputService = new ConsoleInputService();
        OutputService outputService = new ConsoleOutputService();

        int noOfPlayers = inputService.getNoOfPlayersForEachTeam();
        int noOfOvers = inputService.getNoOfOvers();

        if (noOfOvers< Constants.MINIMUM_NUMBER_OF_OVERS){
            throw new InvalidNumberOfOversException();
        }
        if (noOfPlayers<Constants.MINIMUM_NUMBER_OF_PLAYERS){
            throw new InvalidNumberOfPlayersException();
        }

        MatchService matchService = new MatchService(noOfPlayers,noOfOvers,inputService,outputService);
        matchService.startMatch();
    }
}

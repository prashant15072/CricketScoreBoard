package service;

import Utils.Constants;
import Utils.RunType;
import exception.InvalidActionException;
import model.Ball;
import model.Player;
import model.Team;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Scanner;

public class ConsoleInputService implements InputService{
    private Scanner scanner ;

    public ConsoleInputService() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public LinkedList<Player> getBattingOrder(Team team, int noOfPlayersInEachTeam) {
        System.out.println("Batting Order for "+team.getTeamName());
        LinkedList<Player> playerLinkedList = new LinkedList<>();

        for (int i=0;i<noOfPlayersInEachTeam;i++){
            String playerName= scanner.next();
            Player player = Player.builder().name(playerName).build();
            playerLinkedList.addLast(player);
        }

        return playerLinkedList;
    }

    @Override
    public Ball getBall() {
        Ball.BallBuilder ballBuilder = Ball.builder();
        String event = scanner.next();
        if (event.equals(RunType.WICKET.getString())){
            ballBuilder.isWicket(true);
        }
        else {
            Optional<RunType> optionalRunType = Arrays.stream(RunType.values()).filter(r -> r.getString().equals(event)).findFirst();
            if (optionalRunType.isEmpty()){
                throw new InvalidActionException("Invalid number of runs entered!");
            }

            RunType runType  = optionalRunType.get();
            if (runType.isExtras()) {
                ballBuilder.isExtras(true);
            }
            ballBuilder.isCounted(runType.isBallCounted()).runsScored(runType.getRuns()).extraRuns(runType.getExtraRuns());
        }

        return ballBuilder.build();
    }

    @Override
    public int getNoOfOvers() {
        System.out.print("Enter the no of overs:");
        return scanner.nextInt();
    }

    @Override
    public int getNoOfPlayersForEachTeam() {
        System.out.print("Enter the no of players for each team:");
        return scanner.nextInt();
    }
}

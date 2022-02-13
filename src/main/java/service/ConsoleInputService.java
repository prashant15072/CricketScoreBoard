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
    Scanner scanner ;

    public ConsoleInputService() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public LinkedList<Player> getBattingOrder(Team team, int noOfPlayersInEachTeam) {
        System.out.println("Batting Order for "+team.getTeamName());
        LinkedList<Player> playerLinkedList = new LinkedList<>();

        for (int i=0;i<noOfPlayersInEachTeam;i++){
            String playerName= scanner.next();
            Player player = new Player(playerName);
            playerLinkedList.addLast(player);
        }

        return playerLinkedList; //TODO Better to have this as a Queue
    }

    @Override
    public Ball getBall() {
        Ball ball = new Ball();
        String event = scanner.next();
        if (event.equals(RunType.WICKET.getString())){
            ball.setWicket(true);
        }
        else {
            Optional<RunType> optionalRunType = Arrays.stream(RunType.values()).filter(r -> r.getString().equals(event)).findFirst();
            if (optionalRunType.isEmpty()){
                throw new InvalidActionException("Invalid number of runs entered!");
            }

            RunType runType  = optionalRunType.get();
            if (runType.isExtras()) {
                ball.setExtras(true);
            }
            ball.setCounted(runType.isBallCounted());
            ball.setRunsScored(runType.getRuns());
            ball.setExtraRuns(runType.getExtraRuns());
        }

        return ball;
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

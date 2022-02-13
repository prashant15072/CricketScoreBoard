package service;

import model.Ball;
import model.Player;
import model.Team;

import java.util.LinkedList;

public interface InputService {
    LinkedList<Player> getBattingOrder(Team team, int noOfPlayersInEachTeam);

    Ball getBall();

    int getNoOfOvers();

    int getNoOfPlayersForEachTeam();
}

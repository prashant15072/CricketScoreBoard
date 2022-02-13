package service;

import model.Innings;
import model.Player;

import java.util.LinkedList;

public interface OutputService {
    void printOverNumber(int overNumber);

    void printScoreCard(Innings currentInnings, LinkedList<Player> batsmenBatting);
}

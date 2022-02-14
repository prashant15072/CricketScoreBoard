package service;

import model.Innings;
import model.Player;

public interface OutputService {
    void printOverNumber(int overNumber);

    void printScoreCard(Innings currentInnings,Player onStrikebatsmen,Player offStrikebatsmen);
}

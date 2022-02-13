package model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Player {
    String name;
    int runScored;
    int noOfBallsPlayed;
    int noOfFours;
    int noOfSixes;
    int strikeRate;

    public Player(String name){
        this.name = name;
        runScored =0;
        noOfBallsPlayed =0;
        noOfFours=0;
        noOfSixes = 0;
    }

    public double getStrikRate(){
        return (double)runScored/noOfBallsPlayed;
    }

    public void addRuns(int val){
        runScored+=val;
    }
}

package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    private final String name;
    private int runScored = 0;
    private int noOfBallsPlayed = 0;
    private int noOfFours =0;
    private int noOfSixes =0;
    private int strikeRate;

    public double getStrikRate(){
        return (double)runScored/noOfBallsPlayed;
    }

    public void addRuns(int val){
        runScored+=val;
    }
}

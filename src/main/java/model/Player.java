package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    final String name;
    int runScored = 0;
    int noOfBallsPlayed = 0;
    int noOfFours =0;
    int noOfSixes =0;
    int strikeRate;

    public double getStrikRate(){
        return (double)runScored/noOfBallsPlayed;
    }

    public void addRuns(int val){
        runScored+=val;
    }
}

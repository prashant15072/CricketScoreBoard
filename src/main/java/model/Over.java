package model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Over {
    private final int number;
    private int runScored=0;
    private int extras=0;
    private int totalRunsInAnOver=0;

    private final List<Ball> balls;

    public void addExtras(int val){
        extras+=val;
    }

    public void addToTotalRuns(int val){
        totalRunsInAnOver+=val;
    }

    public void addRunScored(int val){
        runScored+=val;
    }

}

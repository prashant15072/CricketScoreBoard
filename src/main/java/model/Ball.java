package model;

import lombok.Data;

@Data
public class Ball {
    private int runsScored;
    private int extraRuns;
    private boolean isExtras;
    private boolean isWicket;
    private boolean isCounted;
}

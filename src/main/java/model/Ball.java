package model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Ball {
    private final int runsScored;
    private final int extraRuns;
    private final boolean isExtras;
    private final boolean isWicket;
    private final boolean isCounted;
}

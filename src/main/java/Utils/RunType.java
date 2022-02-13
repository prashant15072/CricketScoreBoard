package Utils;

public enum RunType {
    WICKET("W",0, 0, true, false),
    WIDE("Wd",0, 1, false, true),
    ONE_WIDE("1Wd",0, 2, false, true),
    TWO_WIDE("2Wd",0, 3, false, true),
    THREE_WIDE("3Wd",0, 4, false, true),
    FOUR_WIDE("4Wd",0, 5, false, true),
    NOBALL("Nb",0, 1, false, true),
    ONE_NOBALL("1Nb",1, 1, false, true),
    TWO_NOBALL("2Nb",2, 1, false, true),
    THREE_NOBALL("3Nb",3, 1, false, true),
    FOUR_NOBALL("4Nb",4, 1, false, true),
    SIX_NOBALL("6Nb",6, 1, false, true),
    LEG_BYE("Lb",0, 0, true,true),
    ONE_LEG_BYE("1Lb",0, 1, true,true),
    TWO_LEG_BYE("2Lb",0, 2, true,true),
    THREE_LEG_BYE("3Lb",0, 3, true,true),
    FOUR_LEG_BYE("4Lb",0, 4, true,true),
    ZERO("0",0, 0, true, false),
    ONE("1",1, 0, true, false),
    TWO("2",2, 0, true, false),
    THREE("3",3, 0, true, false),
    FOUR("4",4, 0, true, false),
    SIX("6",6, 0, true, false);

    private final String stringRunType;
    private final int runs;
    private final int extraRuns;
    private final boolean isBallCounted;
    private final boolean isExtras;
    RunType(String s, int val, int extraRuns, boolean isBallCounted, boolean isExtras) {
        this.stringRunType = s;
        this.runs = val;
        this.extraRuns = extraRuns;
        this.isBallCounted = isBallCounted;
        this.isExtras = isExtras;
    }

    public int getRuns() {
        return runs;
    }

    public boolean isBallCounted() {
        return isBallCounted;
    }

    public boolean isExtras() {
        return isExtras;
    }

    public int getExtraRuns() {
        return extraRuns;
    }

    public String getString() {
        return stringRunType;
    }
}

package duke;

public class Pair {

    private boolean toEnd;
    private String toPrint;

    Pair(boolean toEnd, String toPrint) {
        this.toEnd = toEnd;
        this.toPrint = toPrint;
    }

    boolean getToEnd() {
        return toEnd;
    }

    String getToPrint() {
        return toPrint;
    }
}

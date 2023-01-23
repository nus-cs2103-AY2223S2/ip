import java.util.Objects;

public class Event extends Task {
    private static String from;
    private static String to;

    Event(String description, String from, String to)  {
        super(description);
        this.from = from;
        this.to = to;
        this.symbol = "E";
    }

    Event(String description, String isDone, String from, String to) {
        super(description, isDone);
        this.from = from;
        this.to = to;
        this.symbol = "E";
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + ", to: " + this.to + ")";
    }

    @Override
    public String getDataFormat() {
        return this.combineData(super.getDataFormat(), this.from, this.to);
    }
}

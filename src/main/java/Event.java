import java.time.LocalDate;

public class Event extends Task {
    protected String type;
    protected LocalDate startTime;
    protected LocalDate endTime;

    public Event(String description) {
        super();
        int indexOfFrom = description.indexOf("/from");
        int indexOfTo = description.indexOf("/to");
        this.name = description.substring(0, indexOfFrom - " ".length());
        this.startTime = Task.parseDate(description.substring(indexOfFrom + "/from ".length(), indexOfTo - " ".length()));
        this.endTime = Task.parseDate(description.substring(description.indexOf("/to") + "/to ".length()));
        this.type = "E";
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", type,
                super.toString(), Task.printDate(startTime), Task.printDate(endTime));
    }
}

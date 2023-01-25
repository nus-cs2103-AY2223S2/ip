package tasks;
import java.time.LocalDate;

// Event class returns result that is type [E] and a starting time and an ending
// time
public class Event extends Task {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String startTime;
    private final String endTime;
    private final String[] startingPeriod;
    private final String[] endingPeriod;

    public Event(String name, String startingTime, String endTime) {
        super(name);
        startingPeriod = startingTime.split(" ");
        endingPeriod = endTime.split(" ");
        if (startingPeriod[0].contains("/")) {
            this.startDate = LocalDate.parse(startingPeriod[0].replaceAll("/", "-"));
        } else {
            this.startDate = LocalDate.parse(startingPeriod[0]);
        }

        if (endingPeriod[0].contains("/")) {
            this.endDate = LocalDate.parse(endingPeriod[0].replaceAll("/", "-"));
        } else {
            this.endDate = LocalDate.parse(endingPeriod[0]);
        }

        this.startTime = startingPeriod[1];
        this.endTime = endingPeriod[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + startDate.getDayOfMonth() + " " +
                startDate.getMonth() + " " + startDate.getYear() + ", " + startTime + " to: " +
                endDate.getDayOfMonth() + " " + endDate.getMonth() + " " + endDate.getYear() + ", "
                + endTime + " )";
    }
}

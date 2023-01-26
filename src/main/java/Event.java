import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    protected LocalDate fromDate;
    protected String from;
    protected LocalDate toDate;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        try {
            if (from.split("-").length == 3) {
                this.fromDate = LocalDate.parse(from.substring(1, 11));
            } else {
                this.from = from;
            }
            if (to.split("-").length == 3) {
                this.toDate = LocalDate.parse(to.substring(1, 11));
            } else {
                this.to = to;
            }
        } catch (DateTimeParseException e) {
            System.out.println("INVALID DATE FORMAT, please use xxxx-xx-xx");
        }
    }

    @Override
    public String toString() {
        String s1, s2;
        if (fromDate == null) {
            s1 = from;
        } else {
            s1 = fromDate.toString();
        }
        if (toDate == null) {
            s2 = to;
        } else {
            s2 = toDate.toString();
        }
        return ("[E]" + super.toString() + "(from: " + s1 + " to: " + s2 + ")");
    }

    @Override
    public String toMemory() {
        int i = this.isDone ? 1 : 0;
        String s1, s2;
        if (fromDate == null) {
            s1 = from;
        } else {
            s1 = fromDate.toString();
            // space necessary for loading data the next time
            s1 = ' ' + s1;
        }
        if (toDate == null) {
            s2 = to;
        } else {
            s2 = toDate.toString();
            // space necessary for loading data the next time
            s2 = ' ' + s2;
        }
        return ("E`" + i + "`" + this.description + "`" + s1 + "`" + s2 + '\n');
    }
}

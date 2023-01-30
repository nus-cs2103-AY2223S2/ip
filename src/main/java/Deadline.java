import java.time.LocalDate;
import java.util.*;

//solution adapted from 2103 website
public class Deadline extends Task {

    protected LocalDate endTime;

    static final String type  = "D";

    public Deadline(String name, String endTime) {
        super(name);
        this.endTime = DateTimeParser.parse(endTime);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + endTime.toString() + ")";
    }
}

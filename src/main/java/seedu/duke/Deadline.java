package seedu.duke;

import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * Represents a deadline task
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructs a deadline
     * @param parsed
     * @throws DukeException
     */
    public Deadline(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("deadline"));
        this.by = Parser.stringToDate(parsed.get("/by"));
        abbreviation = 'D';
    }

    /**
     * Converts LocalDateTime to String
     * @return
     */
    private String getDeadlineDate() {
        String month = by.getMonth().toString().substring(0, 3);
        String day = Integer.toString(by.getDayOfMonth());
        String year = Integer.toString(by.getYear());
        String hour = Integer.toString(by.getHour());
        String minute = Integer.toString(by.getMinute());
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        String time = hour + minute;
        return String.format("%s %s %s %s", month, day, year, time);
    }

    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), getDeadlineDate());
    }
}


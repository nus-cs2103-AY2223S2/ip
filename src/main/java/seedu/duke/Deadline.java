package seedu.duke;

import java.time.LocalDateTime;
import java.util.HashMap;

public class Deadline extends Task {

    private LocalDateTime by;

    public Deadline(HashMap<String, String> parsed) throws DukeException {
        super(parsed.get("deadline"));
        this.by = parseDate(parsed.get("/by"));
        abbreviation = 'D';
    }

    private LocalDateTime parseDate(String by) {
        String[] date = by.split(" ")[0].split("/");
        int day = Integer.valueOf(date[0]);
        int month = Integer.valueOf(date[1]);
        int year = Integer.valueOf(date[2]);
        String time = by.split(" ")[1];
        int hour = Integer.valueOf(time.substring(0, 2));
        int minute = Integer.valueOf(time.substring(2, 4));
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute);
        return dateTime;
    }

    private String getBy() {
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
        return super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Generates a string that is to be saved into the save file. The string is formatted such that
     * the TaskList can simply read this string and recreate the Deadline
     * @return a string used to represent this deadline
     */
    public String getBreakdown() {
        String day = Integer.toString(by.getDayOfMonth());
        String month = Integer.toString(by.getMonthValue());
        String year = Integer.toString(by.getYear());
        String hour = Integer.toString(by.getHour());
        String minute = Integer.toString(by.getMinute());
        if (minute.length() == 1) {
            minute = "0" + minute;
        }
        String time = hour + minute;
        return String.format("deadline %s /by %s/%s/%s %s", task, day, month, year, time);
    }
}


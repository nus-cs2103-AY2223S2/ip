package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Deadline extends Task {

    protected String byDate;
    protected String result;
    protected LocalDate d1;
    protected boolean haveFormatErr;

    public Deadline(String description, String by) {
        super(description);
        String[] byArr = by.split(" ");
        this.haveFormatErr = true;
        Pattern pattern = Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})");
        Pattern pattern2 = Pattern.compile("(\\d{2})\\:(\\d{2})");
        Matcher matcher = pattern.matcher(byArr[0].trim());
        Matcher matcher2 = pattern2.matcher(byArr[1]);
        if (matcher.matches() && matcher2.matches()) {
            this.d1 = LocalDate.parse(byArr[0]);
            this.byDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.result = LocalTime.parse(byArr[1], DateTimeFormatter.ofPattern("HH:mm")).format(DateTimeFormatter.ofPattern("hh:mm a"));
            this.haveFormatErr = false;
        }
    }
    public boolean checkFormat() {
        return this.haveFormatErr;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.byDate + " " + result + ")";
    }
}

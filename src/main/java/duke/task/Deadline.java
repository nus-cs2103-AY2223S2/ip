package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is a task object, when it breaks down the deadline into the respective alphanumeric form.
 */
public class Deadline extends Task {

    protected String bydate;
    protected String result;
    protected LocalDate d1;
    protected boolean haveFormatErr;

    /**
     * Constructor for the deadline class.
     *
     * @param description task description in string.
     * @param by          deadline of the task to be completed.
     */
    public Deadline(String description, String by) {
        super(description);
        String[] byarr = by.split(" ");
        this.haveFormatErr = true;
        Pattern pattern = Pattern.compile("(\\d{4})\\-(\\d{2})\\-(\\d{2})");
        Pattern pattern2 = Pattern.compile("(\\d{2})\\:(\\d{2})");
        Matcher matcher = pattern.matcher(byarr[0].trim());
        Matcher matcher2 = pattern2.matcher(byarr[1]);
        if (matcher.matches() && matcher2.matches()) {
            this.d1 = LocalDate.parse(byarr[0]);
            this.bydate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            this.result = LocalTime.parse(byarr[1], DateTimeFormatter.ofPattern("HH:mm"))
                    .format(DateTimeFormatter.ofPattern("hh:mm a"));
            this.haveFormatErr = false;
        }
    }

    /**
     * Checks if the input has an incorrect date and time format.
     *
     * @return boolean - true or false if there is a formatting error.
     */
    public boolean checkFormat() {
        return this.haveFormatErr;
    }

    /**
     * Method that returns a string of the formmated deadline task.
     *
     * @return String Output of the date line object formatted.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.bydate + " " + result + ")";
    }
}

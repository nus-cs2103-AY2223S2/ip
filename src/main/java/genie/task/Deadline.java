package genie.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Deals with creating deadline tasks.
 */
public class Deadline extends Task {
    protected String by; //TODO make by to be of type Date

    /**
     * A constructor that takes in the task descriptor and deadline.
     * @param description of deadline
     * @param by due date of task
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        //this.by = parseDate(by);
    }

    /**
     * Returns deadline as a formatted string to print for list command.                                                                                                                                                                                                          for list command
     * @return deadline
     */
    @Override
    public String toString() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.by);
            String formattedDate = new SimpleDateFormat("EEE, dd/MM/yyyy, h:mm a").format(date);
            this.by = formattedDate;
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        } catch (ParseException e) {
            //return "Sorry, I can't recognize this date format! ><\n" +
            //        "Try this format instead: deadline xxx /by YYYY-MM-DD hh:mm";
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }

    /**
     * Returns deadline as a formatted string to print after loading .txt file.
     * @return deadline
     */
    @Override
    public String toFileFormat() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.by);
            String formattedDate = new SimpleDateFormat("EEE, dd/MM/yyyy, h:mm a").format(date);
            this.by = formattedDate;
            return "[D]" + super.toString() + " | " + formattedDate;
        } catch (ParseException e) {
            //return "Sorry, I can't recognize this date format! ><\n" +
            //        "Try this format instead: deadline xxx /by YYYY-MM-DD hh:mm";
            return "[D]" + super.toString() + " | " + this.by;
        }
    }

    /*
    public String parseDate(String date) {
        try {
            Date d = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.by);
            String formattedDate = new SimpleDateFormat("EEE, dd/MM/yyyy, hma").format(d);
            return formattedDate;
        } catch (ParseException e) {
            //return "Sorry, I can't recognize this date format! ><\n" +
            //        "Try this format instead: deadline xxx /by YYYY-MM-DD hh:mm";
            return date;
        }
    }
    */
}



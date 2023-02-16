package duke.task;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Deadline extends Task {
    protected Date deadLineTime;
    protected String deadLineString;

    /**
     * constructor of Deadline
     *  It has several subclasses including Deadline and Event
     * @param item is name of the task
     * @param type is the type of task
     * @param date is a Date object when the task needs ot be completed by
     * @param dateString is the same as date but stores the date as string
     */
    public Deadline(String item, String type, Date date, String dateString) {
        super(item,type);
        deadLineTime = date;
        deadLineString = dateString;
    }


    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[D]" + super.toString() + "(by: " + convertToString.format(deadLineTime) + ")" + containNotes();
    }

    /**
     * gets the time in which the deadline task suppose to be completed by
     * @return the timing of the deadline
     */
    @Override
    public String getTime() {
        return deadLineString;
    }

}

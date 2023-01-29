package duke.task;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Deadline extends Task {
    protected Date deadLineTime;
    protected String deadLineString;
    public Deadline(String item, String type, Date date, String dateString) {
        super(item,type);
        deadLineTime = date;
        deadLineString = dateString;
    }

    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[D]" + super.toString() + "(by: " + convertToString.format(deadLineTime) + ")";
    }

    @Override
    public String getTime() {
        return deadLineString;
    }


}

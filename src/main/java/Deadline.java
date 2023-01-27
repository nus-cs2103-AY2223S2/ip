import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String by; //TODO make by to be of type Date

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.by);
            String formattedDate = new SimpleDateFormat("EEE, dd/MM/yyyy, hma").format(date);
            this.by = formattedDate;
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        } catch(ParseException e) {
            //return "Sorry, I can't recognize this date format! ><\n" +
            //        "Try this format instead: deadline xxx /by YYYY-MM-DD hh:mm";
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }
    @Override
    public String toFileFormat() {
        return "[D]" + super.toFileFormat() + " | " + this.by;
    }
}



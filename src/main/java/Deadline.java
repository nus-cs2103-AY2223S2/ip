import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    @Override
    public String toString() {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(this.by);
            String formattedDate = new SimpleDateFormat("EEE, dd/MM/yyyy, hma").format(date);
            return "[D]" + super.toString() + " (by: " + formattedDate + ")";
        } catch(ParseException e) {
            //return "Sorry, I can't recognize this date format! ><\n" +
            //        "Try this format instead: deadline xxx /by YYYY-MM-DD hh:mm";
            return "[D]" + super.toString() + " (by: " + this.by + ")";
        }
    }
}



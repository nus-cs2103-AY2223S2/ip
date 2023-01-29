import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Deadline extends Task {
    protected Date deadLineTime;

    public Deadline(String item, String type, Date date) {
        super(item,type);
        deadLineTime = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat convertToString = new SimpleDateFormat("dd MMM yyyy HH:mm a", Locale.ENGLISH);
        return "[D]" + super.toString() + " (by: " + convertToString.format(deadLineTime) + ")";
    }

}

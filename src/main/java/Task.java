import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Task {
    protected String description;
    protected boolean isDone;
    private final String SEPARATOR = "____________________________________________________________";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {

        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {

        return description;
    }

    public void taskDone() {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this.toString());
        System.out.println(SEPARATOR);
        isDone = true;
    }

    public void taskNotDone() {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(this.toString());
        System.out.println(SEPARATOR);
        isDone = false;
    }



    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns a LocalDateTime object that can be stored.
     * <p>
     * This method accepts Strings with the following format dd/MM/yyyy HHmm
     * where the time is in 24 hours.
     * 
     * @param stringDate
     * @return the LocalDateTime representation of the string
     */
    public static LocalDateTime convertDateTime(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return LocalDateTime.parse(stringDate, formatter);
    }

    /**
     * Returns a String object by formatting the LocalDateTime object into the
     * preferred String representation.
     *
     * @param dt
     * @return formatted
     */
    public String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy, HHmm'H'");
        return dt.format(formatter);
    }
}


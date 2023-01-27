<<<<<<< HEAD
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{

    protected LocalDateTime date;

    public Deadlines(String description, String date) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.date = LocalDateTime.parse(date, formatter);

    }
    @Override
    public String toString() {

        return "[D]" + super.toString() +
                "(by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
=======
public class Deadlines extends Task{

    protected String time;

    public Deadlines(String description, String time) {
        super(description);
        this.time = time;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.time + ")";
>>>>>>> branch-Level-7
    }
}

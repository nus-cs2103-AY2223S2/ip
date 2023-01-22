import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected String unFormatted;
    protected String byDate;
    protected String byTime;
    public Deadline(String description, String byDate, String byTime) {
        super(description);
        this.unFormatted = byDate;
        DateTimeFormatter parseFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");
        LocalDate d = LocalDate.parse(byDate, parseFormatter);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        this.byDate = d.format(formatter);
        this.byTime = byTime;
    }

    @Override
    public String getName() {
        return "D";
    }

    public String getDateTime() {
        return this.unFormatted + " " + this.byTime;
    }


    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: " + this.byDate + " " + this.byTime + ")";
    }
}

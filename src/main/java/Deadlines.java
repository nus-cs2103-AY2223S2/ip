import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadlines extends Task{
    protected String ddl;
    protected LocalDateTime by;
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Deadlines(String description, String by) {
        super(description);
        this.ddl = by;
        this.by = LocalDateTime.parse(by);
    }

    public String getBy() {
        return ddl;
    }

    public void getDeadline() {
        System.out.println("The deadline is on" + by.format(format));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(format) + ")";
    }
}

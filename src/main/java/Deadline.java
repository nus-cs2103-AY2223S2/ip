import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;


public class Deadline extends Task{
<<<<<<< Updated upstream
    String tag = "[D]";
    protected String by;
=======
    String tag = "D";
    protected LocalDateTime by;
>>>>>>> Stashed changes

    public Deadline() {
        super.tag = tag;
    }
    public Deadline(String description) {
        super.tag = tag;
        super.description = description;
    }

    @Override
    public void genDscp(String input) throws InvalidDeadline{
        String dscp = input.replace("deadline ", "");
        if (dscp.isBlank()) {
            throw new InvalidDeadline();
        }
        int byId = dscp.indexOf("/by");
        if (byId == -1) {
            throw new InvalidDeadline();
        }
<<<<<<< Updated upstream
        this.by = dscp.substring(byId + 4);
        String task = String.format("%s (by: %s)", dscp.substring(0, byId - 1), this.by);
        super.task = task;
=======
        try {
            this.by = LocalDateTime.parse(input.substring(byId + 4), DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new InvalidDeadline("Please enter the deadline correctly");
        }
        String formattedBy = this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
        String description = String.format("%s (by: %s)", input.substring(0, byId - 1), formattedBy);
        super.description = description;
>>>>>>> Stashed changes
    }

    //Override toString
}

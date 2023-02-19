import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class Event extends Task {
<<<<<<< .merge_file_a10728
<<<<<<< Updated upstream
    String tag = "[E]";
=======
    String tag = "E";
>>>>>>> .merge_file_a17964
    protected String from;
    protected String to;
=======
    String tag = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;
>>>>>>> Stashed changes

    public Event() {
        super.tag = tag;
    }

    public Event(String description) {
        super.tag = tag;
        super.description = description;
    }

    //KEEP ALL WORDS SEPARATED BY SPACES
    @Override
    public void genDscp(String input) throws InvalidEvent {
        if (input.isBlank()) {
            throw new InvalidEvent();
        }
        int fromId = input.indexOf("/from");
        int toId = input.indexOf(("/to"));
        if (fromId == -1 || toId == -1) {
            throw new InvalidEvent();
        }
<<<<<<< .merge_file_a10728
<<<<<<< Updated upstream
        this.from = dscp.substring(fromId + 6, toId - 1);
        this.to = dscp.substring(toId + 4);
        String task = String.format("%s (from: %s to: %s)", dscp.substring(0, fromId - 1), this.from, this.to);
        super.task = task;
=======
        try {
            this.from = LocalDateTime.parse(input.substring(fromId + 6, toId - 1),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(input.substring(toId + 4),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));;
        } catch (DateTimeParseException e) {
            throw new InvalidEvent("Please enter date correctly");
        }
        String formattedFrom = this.from.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));
        String formattedTo = this.to.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm"));

        String description = String.format("%s (from: %s to: %s)", input.substring(0, fromId - 1),
                formattedFrom, formattedTo);
        super.description = description;
>>>>>>> Stashed changes
=======
        this.from = input.substring(fromId + 6, toId - 1);
        this.to = input.substring(toId + 4);
        String description = String.format("%s (from: %s to: %s)", input.substring(0, fromId - 1), this.from, this.to);
        super.description = description;
>>>>>>> .merge_file_a17964
    }

    //Override toString
}

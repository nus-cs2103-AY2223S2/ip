import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    String tag = "E";
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event() {
        super.tag = tag;
    }

    public Event(String description) {
        super.tag = tag;
        super.description = description;
    }

    //KEEP ALL WORDS SEPARATED BY SPACES
    @Override
    public void genDscp(String input) throws InvalidEvent{
        String dscp = input.replace("event ", "");
        if (dscp.isBlank()) {
            throw new InvalidEvent();
        }
        int fromId = dscp.indexOf("/from");
        int toId = dscp.indexOf(("/to"));
        if (fromId == -1 || toId == -1) {
            throw new InvalidEvent();
        }

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
    }
}

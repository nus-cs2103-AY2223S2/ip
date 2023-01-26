import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{

    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
    private static final DateTimeFormatter FORMATTER  = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static Event create(String str) throws DukeException{
        if (str.length() < 1) {
            throw new DukeException();
        } else {
            String[] text = str.substring(1).split(" /");
            if (text.length < 3) {
                throw new DukeException();
            } else {
                String desc = text[0];
                String from = text[1].substring(5);
                String to = text[2].substring(3);
                return new Event(desc, LocalDateTime.parse(from, Event.FORMATTER), LocalDateTime.parse(to, Event.FORMATTER));
            }
        }
    }

    public Event(String description, LocalDateTime startTime, LocalDateTime endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }



    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")) + " to: " + this.endTime.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm")) + ")";
    }
}
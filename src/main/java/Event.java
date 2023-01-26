import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    Event (String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E||" + super.toSaveFormat() + "||" + this.from + "||" + this.to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(formatter) + ", to: " + this.to.format(formatter) + ")";
    }

    public static Event fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        Event generatedEvent = new Event(inputs[2], inputs[3], inputs[4]);
        if (inputs[1].equals("1")) {
            generatedEvent.setCompleted(true);
        }
        return generatedEvent;
    }
}

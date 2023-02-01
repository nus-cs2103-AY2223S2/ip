package duke.task;

import java.time.LocalDateTime;

import static duke.utils.FormatHelper.PRINTFORMAT;
import static duke.utils.FormatHelper.INPUTFORMAT;

public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    public Event (String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toSaveFormat() {
        return "E||" + super.toSaveFormat() + "||" + this.from.format(INPUTFORMAT) + "||" + this.to.format(INPUTFORMAT);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(PRINTFORMAT) + ", to: " + this.to.format(PRINTFORMAT) + ")";
    }

    public static Event fromSaveFormat(String savedData) {
        String[] inputs = savedData.split("\\|\\|");
        LocalDateTime parseFrom = LocalDateTime.parse(inputs[3], INPUTFORMAT);
        LocalDateTime parseTo = LocalDateTime.parse(inputs[4], INPUTFORMAT);
        Event generatedEvent = new Event(inputs[2], parseFrom, parseTo);
        if (inputs[1].equals("1")) {
            generatedEvent.setCompleted(true);
        }
        return generatedEvent;
    }
}
